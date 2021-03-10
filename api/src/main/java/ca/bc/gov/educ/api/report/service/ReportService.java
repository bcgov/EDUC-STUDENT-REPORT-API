package ca.bc.gov.educ.api.report.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import ca.bc.gov.educ.api.report.dto.GenerateReport;
import ca.bc.gov.educ.api.report.dto.ReportOptions;
import ca.bc.gov.educ.api.report.dto.ReportTemplate;
import ca.bc.gov.educ.api.report.dto.ResponseObj;
import ca.bc.gov.educ.api.report.dto.StudentAssessment;
import ca.bc.gov.educ.api.report.dto.StudentCourse;
import ca.bc.gov.educ.api.report.dto.StudentCourseAssessment;
import ca.bc.gov.educ.api.report.template.AchievementReportTemplate;
import ca.bc.gov.educ.api.report.template.TranscriptReportTemplate;
import ca.bc.gov.educ.api.report.util.ReportApiConstants;
import ca.bc.gov.educ.api.report.util.ReportApiUtils;

@Service
public class ReportService {

    private static Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Value(ReportApiConstants.ENDPOINT_GET_PDF_FROM_HTML_URL)
    private String getPDFfromHTMLURL;
    
    @Value(ReportApiConstants.ENDPOINT_GET_PDF_URL)
    private String getPDF;
    
    @Value(ReportApiConstants.ENDPOINT_GET_TOKEN_URL)
    private String getToken;
    
    @Autowired
    RestTemplate restTemplate;
    
    @Value("${report.render.user}")
	private String uName;

	@Value("${report.render.password}")
	private String pass;

    /**
     * Get Student Achievement Report
     *
     * @return achievementReportAsPdf
     */
    public ResponseEntity<byte[]> getStudentAchievementReport(Map<String, String> reportParameters) {

        AchievementReportTemplate achievementReport = new AchievementReportTemplate(reportParameters);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(
                new ByteArrayHttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<String> entity = new HttpEntity<String>(achievementReport.getHtmlTemplate().toString(), headers);

        ResponseEntity<byte[]> achievementReportAsPdf = restTemplate.exchange(
                getPDFfromHTMLURL, HttpMethod.POST, entity, byte[].class, "1");

        return achievementReportAsPdf;
    }

    /**
     * Get Student Transcript Report
     *
     * @return GraduationData
     */
    public ResponseEntity<byte[]> getStudentTranscriptReport(Map<String, String> reportParameters) {

        TranscriptReportTemplate transcriptReport = new TranscriptReportTemplate(reportParameters);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(
                new ByteArrayHttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<String> entity = new HttpEntity<String>(transcriptReport.getHtmlTemplate().toString(), headers);

        ResponseEntity<byte[]> transcriptReportAsPdf = restTemplate.exchange(
                getPDFfromHTMLURL, HttpMethod.POST, entity, byte[].class, "1");

        return transcriptReportAsPdf;
    }
    
    public ResponseEntity<byte[]> getStudentAchievementReportCdogs(GenerateReport report) {
    	InputStream inputStream = null;
    	if(report.getData().getStudentExam() != null && report.getData().getStudentExam().size() > 0) {
    		inputStream = getClass().getResourceAsStream("/templates/student_achievement_report_inc_exam_template.docx");
		}else {
			inputStream = getClass().getResourceAsStream("/templates/student_achievement_report_template.docx");
		}
		try {
			//report.getData().setIsaDate(ReportApiUtils.formatDate(new Date(),"yyyyMMdd"));
			File tempFile = File.createTempFile("student_achievement_report_template", ".docx");
			FileOutputStream out = new FileOutputStream(tempFile);
			IOUtils.copy(inputStream, out);
			byte[] reportByteArr = FileUtils.readFileToByteArray(tempFile);
			byte[] encoded = Base64.encodeBase64(reportByteArr);
			String encodedString = new String(encoded,StandardCharsets.US_ASCII);
			ReportTemplate template = new ReportTemplate();
			template.setContent(encodedString);
			report.setOptions(new ReportOptions("achievement"));
			report.setTemplate(template);		    
			
			//Getting Token
			HttpHeaders httpHeaders = ReportApiUtils.getHeaders(uName,pass);
			MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
			map.add("grant_type", "client_credentials");
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, httpHeaders);
			ResponseObj res = restTemplate.exchange(getToken, HttpMethod.POST,
					request, ResponseObj.class).getBody();
			
			//Making CDOG call
			HttpHeaders httpCdogsHeaders = ReportApiUtils.getHeaders(res.getAccess_token());
			byte[] ress = restTemplate.exchange(getPDF, HttpMethod.POST,
							new HttpEntity<>(report,httpCdogsHeaders), byte[].class).getBody();
			//ByteArrayInputStream bis = new ByteArrayInputStream(ress);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=studentachievementreport.pdf");
			tempFile.delete();
			return ResponseEntity
			        .ok()
			        .headers(headers)
			        .contentType(MediaType.APPLICATION_PDF)
			        .body(ress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    	
    }

	public ResponseEntity<byte[]> getStudentTranscriptReportCDogs(GenerateReport report) {
		List<StudentCourse> studentCourseList = report.getData().getStudentCourse();
		List<StudentCourse> operatedList = new ArrayList<>();
		studentCourseList.stream()
		  .filter(sC -> sC.isFailed())
		  .filter(sC -> sC.isDuplicate())
		  .forEach(operatedList::add);
		studentCourseList.removeAll(operatedList);
		List<StudentCourseAssessment> studentCourseAssesmentList = prepareCourseList(studentCourseList);
		prepareAssessmentList(report.getData().getStudentAssessment(),studentCourseAssesmentList);
		report.getData().setStudentCourseAssessment(studentCourseAssesmentList);
		InputStream inputStream = null;
		inputStream = getInputStream(report,inputStream,studentCourseAssesmentList);
		
		try {
			//report.getData().setIsaDate(ReportApiUtils.formatDate(new Date(),"yyyyMMdd"));
			File tempFile = File.createTempFile("student_transcript_report_template", ".docx");
			FileOutputStream out = new FileOutputStream(tempFile);
			IOUtils.copy(inputStream, out);
			//addImageToTemplate(tempFile);
			byte[] reportByteArr = FileUtils.readFileToByteArray(tempFile);
			byte[] encoded = Base64.encodeBase64(reportByteArr);
			String encodedString = new String(encoded,StandardCharsets.US_ASCII);
			ReportTemplate template = new ReportTemplate();
			template.setContent(encodedString);
			report.setOptions(new ReportOptions("transcript"));
			report.setTemplate(template);		    
			
			//Getting Token
			HttpHeaders httpHeaders = ReportApiUtils.getHeaders(uName,pass);
			MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
			map.add("grant_type", "client_credentials");
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, httpHeaders);
			ResponseObj res = restTemplate.exchange(getToken, HttpMethod.POST,
					request, ResponseObj.class).getBody();
			
			//Making CDOG call
			HttpHeaders httpCdogsHeaders = ReportApiUtils.getHeaders(res.getAccess_token());
			byte[] ress = restTemplate.exchange(getPDF, HttpMethod.POST,
							new HttpEntity<>(report,httpCdogsHeaders), byte[].class).getBody();
			//ByteArrayInputStream bis = new ByteArrayInputStream(ress);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=studenttranscriptreport.pdf");
			tempFile.delete();
			return ResponseEntity
			        .ok()
			        .headers(headers)
			        .contentType(MediaType.APPLICATION_PDF)
			        .body(ress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	private File addImageToTemplate(File tempFile) {
		WordprocessingMLPackage wPackage = WordprocessingMLPackage.load(tempFile);
        MainDocumentPart mainDocumentPart = wPackage.getMainDocumentPart();
		Document wmlDoc = (Document) mainDocumentPart.getJaxbElement();
		Body body = wmlDoc.getBody();
		        // Extract all paragraphs in the body
		List<Object> paragraphs = body.getContent();
		        // cursor to extract bookmarks and create bookmarks
		RangeFinder rt = new RangeFinder("CTBookmark", "CTMarkupRange");

        // traverse bookmarks
		for (CTBookmark bm:rt.getStarts()) {
		   if (bm.getName().equals("ministerOfEducation")){             
		       InputStream is = new FileInputStream("C:\\Users\\s.karekkattumanasree\\Documents\\sign-sree.PNG");
		       byte[] bytes = IOUtils.toByteArray(is);
		       BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wPackage, bytes);
		       Inline inline = imagePart.createImageInline(null, null, 0,1, false, 50);		                        
		       P p = (P)(bm.getParent());
		       
		       ObjectFactory factory = new ObjectFactory();
		                        // The R object is an anonymous complex type, but I don't know the specific meaning. I guess it is necessary to take a look at ooxml.
		       R run = factory.create();
		                        // drawing is understood as a canvas?
		       Drawing drawing = factory.createDrawing();
		       drawing.getAnchorOrInline().add(inline);
		       run.getContent().add(drawing);
		       p.getContent().add(run);
		   }
		}
		wPackage.save();		
	}
	*/

	private InputStream getInputStream(GenerateReport report, InputStream inputStream, List<StudentCourseAssessment> studentCourseAssesmentList) {
		if(!report.getData().getDemographics().getMinCode().substring(0, 3).equalsIgnoreCase("098")) {
	    	if(studentCourseAssesmentList != null && studentCourseAssesmentList.size() < 22) {
	    		inputStream = getClass().getResourceAsStream("/templates/student_transcript_report_bc_template.docx");
			}else {
				inputStream = getClass().getResourceAsStream("/templates/student_transcript_report_bc_multiple_template.docx");
			}
		}else {
			if(studentCourseAssesmentList != null && studentCourseAssesmentList.size() < 22) {
	    		inputStream = getClass().getResourceAsStream("/templates/student_transcript_report_yukon_template.docx");
			}else {
				inputStream = getClass().getResourceAsStream("/templates/student_transcript_report_yukon_multiple_template.docx");
			}
		}
		
		modifyReportDataBasedOnListSize(report);
		return inputStream;
	}

	private void modifyReportDataBasedOnListSize(GenerateReport report) {
		StudentCourseAssessment secondLastRecord = new StudentCourseAssessment();
		secondLastRecord.setCourseName(" ");
		report.getData().getStudentCourseAssessment().add(secondLastRecord);	
		StudentCourseAssessment lastRecord = new StudentCourseAssessment();
		secondLastRecord.setCourseName("*** End of Course / Assessment List ***");
		report.getData().getStudentCourseAssessment().add(lastRecord);		
	}

	private List<StudentCourseAssessment> prepareAssessmentList(List<StudentAssessment> studentAssessment,
			List<StudentCourseAssessment> studentCourseAssessmentList) {
		Collections.sort(studentAssessment, Comparator.comparing(StudentAssessment::getAssessmentCode));
		studentAssessment.stream().forEach(sA -> {
			StudentCourseAssessment scA = new StudentCourseAssessment();
			scA.setCourseCode(sA.getAssessmentCode());
			scA.setCourseName(sA.getAssessmentName());
			scA.setSessionDate(sA.getSessionDate());
			if(sA.getAssessmentCode().equalsIgnoreCase("LTE10") || sA.getAssessmentCode().equalsIgnoreCase("LTP10")) {
				scA.setFinalPercentage("RM");
			}else {
				if(sA.getSpecialCase() != null) {
					if(sA.getSpecialCase().equalsIgnoreCase("A")) {
						scA.setFinalPercentage("AEG");
					}else if(sA.getSpecialCase().equalsIgnoreCase("E")) {
						scA.setFinalPercentage("AEG");
					}else {
						scA.setFinalPercentage(sA.getProficiencyScore() != null ? sA.getProficiencyScore().toString() : null);
					}
				}
			}
			studentCourseAssessmentList.add(scA);
		});
		return studentCourseAssessmentList;
		
	}

	private List<StudentCourseAssessment> prepareCourseList(List<StudentCourse> studentCourseList) {
		List<StudentCourseAssessment> studentCourseAssessmentList = new ArrayList<StudentCourseAssessment>();
		studentCourseList.stream().forEach(sC -> {
			StudentCourseAssessment scA = new StudentCourseAssessment();
			scA.setCourseCode(sC.getCourseCode());
			scA.setCourseName(sC.getCourseName());
			scA.setCourseLevel(sC.getCourseLevel());
			scA.setCredits(sC.getCredits());
			scA.setFinalLetterGrade(sC.getCompletedCourseLetterGrade());
			scA.setFinalPercentage(sC.getCompletedCoursePercentage().toString());
			scA.setGradReqMet(sC.getGradReqMet());
			scA.setSessionDate(sC.getSessionDate());
			studentCourseAssessmentList.add(scA);
		});
		Collections.sort(studentCourseAssessmentList, Comparator.comparing(StudentCourseAssessment::getCourseLevel)
	                .thenComparing(StudentCourseAssessment::getCourseName));
		return studentCourseAssessmentList;
	}

}

package ca.bc.gov.educ.api.report.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import ca.bc.gov.educ.api.report.dto.GenerateReport;
import ca.bc.gov.educ.api.report.dto.ReportTemplate;
import ca.bc.gov.educ.api.report.dto.ResponseObj;
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
    
    public ResponseEntity<byte[]> getStudentAchievementReport(GenerateReport report) {
		try {
			File file = ResourceUtils.getFile("classpath:student_achievement_report_template.docx");
			byte[] reportByteArr = FileUtils.readFileToByteArray(file);
			byte[] encoded = Base64.encodeBase64(reportByteArr);
		    String encodedString = new String(encoded,StandardCharsets.US_ASCII);
		    ReportTemplate template = new ReportTemplate();
		    template.setContent(encodedString);
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
		    ByteArrayInputStream bis = new ByteArrayInputStream(ress);
		    HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=studentachievementreport.pdf");
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

}

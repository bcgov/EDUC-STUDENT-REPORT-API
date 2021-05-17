package ca.bc.gov.educ.api.report.service;

import ca.bc.gov.educ.api.report.dto.GenerateReport;
import ca.bc.gov.educ.api.report.dto.StudentAssessment;
import ca.bc.gov.educ.api.report.dto.StudentCourse;
import ca.bc.gov.educ.api.report.dto.StudentCourseAssessment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ReportService {

    private static Logger logger = LoggerFactory.getLogger(ReportService.class);

    public ResponseEntity<byte[]> getStudentAchievementReport(GenerateReport report) {
		try {
			byte[] resultBinary = null;
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=studentachievementreport.pdf");
			return ResponseEntity
			        .ok()
			        .headers(headers)
			        .contentType(MediaType.APPLICATION_PDF)
			        .body(resultBinary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    	
    }

	public ResponseEntity<byte[]> getStudentTranscriptReport(GenerateReport report) {
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

		try {

			byte[] reportBinary = null;
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=studenttranscriptreport.pdf");
			return ResponseEntity
			        .ok()
			        .headers(headers)
			        .contentType(MediaType.APPLICATION_PDF)
			        .body(reportBinary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ResponseEntity<byte[]> getStudentCertificateReport(GenerateReport report) {
		
		try {

			byte[] resultBinary = null;
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=studentcertificate.pdf");
			return ResponseEntity
			        .ok()
			        .headers(headers)
			        .contentType(MediaType.APPLICATION_PDF)
			        .body(resultBinary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResponseEntity<byte[]> getStudentVerificationReport(GenerateReport report) {

		try {

			byte[] resultBinary = null;
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=studentverification.pdf");
			return ResponseEntity
					.ok()
					.headers(headers)
					.contentType(MediaType.APPLICATION_PDF)
					.body(resultBinary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
		List<StudentCourseAssessment> studentCourseAssessmentList = new ArrayList<>();
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

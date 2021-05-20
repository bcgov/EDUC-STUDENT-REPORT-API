package ca.bc.gov.educ.grad.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ReportData implements Serializable {

	private String isaDate;
	private String transcriptBanner;
	private String issueDate;
	private School school;
	private StudentInformation studentInformation;
	private StudentDemographics demographics;
	private List<StudentCourse> studentCourse;
	private List<StudentAssessment> studentAssessment;
	private List<StudentExam> studentExam;
	private GraduationMessages graduationMessages;
	private List<StudentCourseAssessment> studentCourseAssessment;
	private String studentName;
	private String studentSchool;
	private String studentCertificateDate;
	
}

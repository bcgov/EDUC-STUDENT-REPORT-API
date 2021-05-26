package ca.bc.gov.educ.grad.dto;

import ca.bc.gov.educ.isd.cert.Certificate;
import ca.bc.gov.educ.isd.grad.GradProgram;
import ca.bc.gov.educ.isd.grad.NonGradReason;
import ca.bc.gov.educ.isd.school.School;
import ca.bc.gov.educ.isd.student.Student;
import ca.bc.gov.educ.isd.transcript.Transcript;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Component
public class ReportData implements Serializable {

	private Student student;
	private School school;
	private String logo;
	private Transcript transcript;
	private GradProgram gradProgram;
	private List<NonGradReason> nonGradReasons;
	private String gradMessage;
	private Date updateDate;
	private Map<String, String> parameters;
	private Certificate certificate;
	
}

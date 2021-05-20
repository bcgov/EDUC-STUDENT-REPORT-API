package ca.bc.gov.educ.grad.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class GraduationMessages implements Serializable {
	private String gradProgram;
	private String gradMessage;
	private String honours;
	private String gpa;
	private boolean hasSpecialProgram;
	private boolean hasCareerProgram;
	private boolean hasCertificates;
	private List<Code> specialProgram;
	private List<Code> careerProgram;
	private List<Code> certificateProgram;
	private List<GradRequirement> nonGradReasons;
}

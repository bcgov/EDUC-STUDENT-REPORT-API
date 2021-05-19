package ca.bc.gov.educ.api.report.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class GraduationMessages {
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

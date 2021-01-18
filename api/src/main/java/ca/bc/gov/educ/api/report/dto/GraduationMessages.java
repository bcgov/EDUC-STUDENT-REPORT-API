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
	private List<CodeDTO> participatedProgram;
	private List<CodeDTO> certificateProgram;
	private List<ReasonDTO> nonGradReasons;
}
package ca.bc.gov.educ.api.report.dto;

import lombok.Data;

@Data
public class GenerateReport {

	private ReportData data;
	private ReportOptions options;
	private ReportTemplate template;
}

package ca.bc.gov.educ.api.report.dto;

import lombok.Data;

@Data
public class ReportOptions {

	private boolean cacheReport= false;
	private String convertTo = "pdf";
	private boolean overwrite = true;
	private String reportName= "studentachievementreport.pdf";
}

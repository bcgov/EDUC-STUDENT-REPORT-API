package ca.bc.gov.educ.grad.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReportOptions implements Serializable {

	private boolean cacheReport;
	private String convertTo;
	private boolean overwrite;
	private String reportName;
	private String reportFile;
	
	public ReportOptions (String reportName) {
		if(reportName.equalsIgnoreCase("achievement")) {
			this.cacheReport = false;
			this.convertTo = "pdf";
			this.overwrite = true;
			this.reportFile = "studentachievementreport.pdf";
		}else if(reportName.equalsIgnoreCase("transcript")) {
			this.cacheReport = false;
			this.convertTo = "pdf";
			this.overwrite = true;
			this.reportFile = "studenttranscriptreport.pdf";
		}else {
			this.cacheReport = false;
			this.convertTo = "pdf";
			this.overwrite = true;
			this.reportFile = "studentcertificate.pdf";
		}
	}
}

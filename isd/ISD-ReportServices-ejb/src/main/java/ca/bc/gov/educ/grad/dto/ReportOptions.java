package ca.bc.gov.educ.grad.dto;

import ca.bc.gov.educ.isd.grad.impl.NonGradReasonImpl;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.io.Serializable;

@Data
@JsonSubTypes({@JsonSubTypes.Type(value = ReportOptions.class, name = "options")})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class ReportOptions implements Serializable {

	private boolean cacheReport;
	private String convertTo;
	private boolean overwrite;
	private String reportName;
	private String reportFile;

	public ReportOptions() {
	}

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

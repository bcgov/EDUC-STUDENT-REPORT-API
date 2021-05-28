package ca.bc.gov.educ.grad.dto;

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
		switch(reportName) {
			case "achievement":
				this.cacheReport = false;
				this.convertTo = "pdf";
				this.overwrite = true;
				this.reportFile = "studentAchievementreport.pdf";
				break;
			case "transcript":
				this.cacheReport = false;
				this.convertTo = "pdf";
				this.overwrite = true;
				this.reportFile = "studentTranscriptreport.pdf";
				break;
			case "certificate":
				this.cacheReport = false;
				this.convertTo = "pdf";
				this.overwrite = true;
				this.reportFile = "studentCertificate.pdf";
				break;
			default:
				throw new RuntimeException("Unknown Report");
		}
	}
}

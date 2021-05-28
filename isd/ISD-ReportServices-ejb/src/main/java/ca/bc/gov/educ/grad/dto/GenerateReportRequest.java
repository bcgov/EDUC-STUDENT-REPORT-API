package ca.bc.gov.educ.grad.dto;

import ca.bc.gov.educ.isd.grad.impl.CertificateImpl;
import ca.bc.gov.educ.isd.grad.impl.GradProgramImpl;
import ca.bc.gov.educ.isd.grad.impl.NonGradReasonImpl;
import ca.bc.gov.educ.isd.student.impl.SchoolImpl;
import ca.bc.gov.educ.isd.student.impl.StudentImpl;
import ca.bc.gov.educ.isd.transcript.impl.TranscriptImpl;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.io.Serializable;

@JsonSubTypes({
		@JsonSubTypes.Type(value = ReportData.class, name = "data"),
		@JsonSubTypes.Type(value = ReportOptions.class, name = "options"),
		@JsonSubTypes.Type(value = StudentImpl.class, name = "student"),
		@JsonSubTypes.Type(value = SchoolImpl.class, name = "school"),
		@JsonSubTypes.Type(value = TranscriptImpl.class, name = "transcript"),
		@JsonSubTypes.Type(value = GradProgramImpl.class, name = "gradProgram"),
		@JsonSubTypes.Type(value = NonGradReasonImpl.class, name = "nonGradReasons"),
		@JsonSubTypes.Type(value = CertificateImpl.class, name = "certificate")
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class GenerateReportRequest implements Serializable {

	private ReportData data;
	private ReportOptions options;

	public ReportData getData() {
		return data;
	}

	public void setData(ReportData data) {
		this.data = data;
	}

	public ReportOptions getOptions() {
		return options;
	}

	public void setOptions(ReportOptions options) {
		this.options = options;
	}
}

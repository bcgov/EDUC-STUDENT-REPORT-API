package ca.bc.gov.educ.grad.dto;

import ca.bc.gov.educ.isd.grad.impl.CertificateImpl;
import ca.bc.gov.educ.isd.grad.impl.GradProgramImpl;
import ca.bc.gov.educ.isd.grad.impl.NonGradReasonImpl;
import ca.bc.gov.educ.isd.student.impl.SchoolImpl;
import ca.bc.gov.educ.isd.student.impl.StudentImpl;
import ca.bc.gov.educ.isd.transcript.impl.TranscriptImpl;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.io.Serializable;

@Data
@JsonSubTypes({
		@JsonSubTypes.Type(value = ReportData.class, name = "data"),
		@JsonSubTypes.Type(value = ReportOptions.class, name = "options")
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class GenerateReportRequest implements Serializable {

	private ReportData data;
	private ReportOptions options;

}

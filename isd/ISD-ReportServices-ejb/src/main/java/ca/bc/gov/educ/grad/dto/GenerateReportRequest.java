package ca.bc.gov.educ.grad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonPropertyOrder({ "options", "data" })
public class GenerateReportRequest implements Serializable {

	private ReportData data;
	private ReportOptions options;

	@JsonProperty("data")
	public ReportData getData() {
		return data;
	}
	public void setData(ReportData data) {
		this.data = data;
	}

	@JsonProperty("options")
	public ReportOptions getOptions() {
		return options;
	}
	public void setOptions(ReportOptions options) {
		this.options = options;
	}
}

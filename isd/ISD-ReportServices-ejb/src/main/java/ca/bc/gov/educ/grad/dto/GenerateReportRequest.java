package ca.bc.gov.educ.grad.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GenerateReportRequest implements Serializable {

	private ReportData data;
	private ReportOptions options;

}

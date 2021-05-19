package ca.bc.gov.educ.api.report.dto;

import lombok.Data;

@Data
public class Report {

    private String reportCode;
    private String contentType;
    private byte[] content;

}

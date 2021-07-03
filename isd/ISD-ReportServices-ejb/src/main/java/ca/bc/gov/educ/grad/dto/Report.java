package ca.bc.gov.educ.grad.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Report implements Serializable {

    private String reportCode;
    private String contentType;
    private byte[] content;

}

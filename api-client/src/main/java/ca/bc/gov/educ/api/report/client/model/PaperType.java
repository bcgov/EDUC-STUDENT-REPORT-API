package ca.bc.gov.educ.api.report.client.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class PaperType {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
    }
}

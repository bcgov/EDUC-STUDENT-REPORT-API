package ca.bc.gov.educ.api.report.service;

import ca.bc.gov.educ.api.report.template.AchievementReportTemplate;
import ca.bc.gov.educ.api.report.template.TranscriptReportTemplate;
import ca.bc.gov.educ.api.report.util.ReportApiConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

public class ReportService {

    @Value(ReportApiConstants.ENDPOINT_GET_PDF_FROM_HTML_URL)
    private String getPDFfromHTMLURL;

    /**
     * Get Student Achievement Report
     *
     * @return achievementReportAsPdf
     */
    public ResponseEntity<byte[]> getStudentAchievementReport(Map<String, String> reportParameters) {

        AchievementReportTemplate achievementReport = new AchievementReportTemplate(reportParameters);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(
                new ByteArrayHttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<String> entity = new HttpEntity<String>(achievementReport.getHtmlTemplate().toString(), headers);

        ResponseEntity<byte[]> achievementReportAsPdf = restTemplate.exchange(
                getPDFfromHTMLURL, HttpMethod.POST, entity, byte[].class, "1");

        return achievementReportAsPdf;
    }

    /**
     * Get Student Transcript Report
     *
     * @return GraduationData
     */
    public ResponseEntity<byte[]> getStudentTranscriptReport(Map<String, String> reportParameters) {

        TranscriptReportTemplate transcriptReport = new TranscriptReportTemplate(reportParameters);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(
                new ByteArrayHttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<String> entity = new HttpEntity<String>(transcriptReport.getHtmlTemplate().toString(), headers);

        ResponseEntity<byte[]> transcriptReportAsPdf = restTemplate.exchange(
                getPDFfromHTMLURL, HttpMethod.POST, entity, byte[].class, "1");

        return transcriptReportAsPdf;
    }

}

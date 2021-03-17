package ca.bc.gov.educ.api.report.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.educ.api.report.dto.GenerateReport;
import ca.bc.gov.educ.api.report.service.ReportService;
import ca.bc.gov.educ.api.report.util.PermissionsContants;
import ca.bc.gov.educ.api.report.util.ReportApiConstants;

@CrossOrigin
@RestController
@RequestMapping (ReportApiConstants.REPORT_API_ROOT_MAPPING)
@EnableResourceServer
public class ReportController {

    private static Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    ReportService reportService;

    @PostMapping (ReportApiConstants.STUDENT_ACHIEVEMENT_REPORT)
    public ResponseEntity<byte[]> getStudentAchievementReport(@RequestBody Map<String, String> reportParameters) {
        logger.debug("Get Student Achievement Report");
        return reportService.getStudentAchievementReport(reportParameters);
    }

    @PostMapping (ReportApiConstants.STUDENT_TRANSCRIPT_REPORT)
    public ResponseEntity<byte[]> getStudentTranscriptReport(@RequestBody Map<String, String> reportParameters) {
        logger.debug("Get Student ranscript Report");
        return reportService.getStudentTranscriptReport(reportParameters);
    }
    
    
    @PostMapping (ReportApiConstants.STUDENT_ACHIEVEMENT_REPORT_CDOGS)
    @PreAuthorize(PermissionsContants.STUDENT_ACHIEVEMENT_REPORT)
    public ResponseEntity<byte[]> getStudentAchievementReportCDogs(@RequestBody GenerateReport report) {
        logger.debug("getStudentAchievementReportCDogs"); 
        return reportService.getStudentAchievementReportCdogs(report);
    }
    
    @PostMapping (ReportApiConstants.STUDENT_TRANSCRIPT_REPORT_CDOGS)
    @PreAuthorize(PermissionsContants.STUDENT_TRANSCRIPT_REPORT)
    public ResponseEntity<byte[]> getStudentTranscriptReportCDogs(@RequestBody GenerateReport report) {
        logger.debug("getStudentTranscriptReportCDogs"); 
        return reportService.getStudentTranscriptReportCDogs(report);
    }
    
    @PostMapping (ReportApiConstants.STUDENT_CERTIFICATE_CDOGS)
    @PreAuthorize(PermissionsContants.STUDENT_CERTIFICATE)
    public ResponseEntity<byte[]> getStudentCertificateCDogs(@RequestBody GenerateReport report) {
        logger.debug("getStudentCertificateCDogs"); 
        return reportService.getStudentCertificateCDogs(report);
    }
}

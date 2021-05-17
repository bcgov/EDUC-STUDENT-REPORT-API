package ca.bc.gov.educ.api.report.controller;

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
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin
@RestController
@RequestMapping (ReportApiConstants.REPORT_API_ROOT_MAPPING)
@EnableResourceServer
@OpenAPIDefinition(info = @Info(title = "API for Report Generation", description = "This API is for Report Generation", version = "1"), security = {@SecurityRequirement(name = "OAUTH2", scopes = {"READ_GRAD_STUDENT_COURSE_DATA"})})
public class ReportController {

    private static Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    ReportService reportService;
    
    @PostMapping (ReportApiConstants.STUDENT_ACHIEVEMENT_REPORT)
    @PreAuthorize(PermissionsContants.STUDENT_ACHIEVEMENT_REPORT)
    @Operation(summary = "Generate Student Achievement Report", description = "Generate Student Achievement Report", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentAchievementReport(@RequestBody GenerateReport report) {
        logger.debug("getStudentAchievementReportCDogs");
        return reportService.getStudentAchievementReport(report);
    }
    
    @PostMapping (ReportApiConstants.STUDENT_TRANSCRIPT_REPORT)
    @PreAuthorize(PermissionsContants.STUDENT_TRANSCRIPT_REPORT)
    @Operation(summary = "Generate Student Transcript Report", description = "Generate Student Transcript Report", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentTranscriptReport(@RequestBody GenerateReport report) {
        logger.debug("getStudentTranscriptReportCDogs"); 
        return reportService.getStudentTranscriptReport(report);
    }
    
    @PostMapping (ReportApiConstants.STUDENT_CERTIFICATE)
    @PreAuthorize(PermissionsContants.STUDENT_CERTIFICATE)
    @Operation(summary = "Generate Student Certificate", description = "Generate Student Certificate", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentCertificate(@RequestBody GenerateReport report) {
        logger.debug("getStudentCertificateCDogs"); 
        return reportService.getStudentCertificateReport(report);
    }
}

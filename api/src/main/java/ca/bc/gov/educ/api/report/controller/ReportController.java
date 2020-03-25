package ca.bc.gov.educ.api.report.controller;

import ca.bc.gov.educ.api.report.service.ReportService;
import ca.bc.gov.educ.api.report.util.ReportApiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping (ReportApiConstants.REPORT_API_ROOT_MAPPING)
public class ReportController {

    private static Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    ReportService reportService;

    @GetMapping (ReportApiConstants.STUDENT_ACHIEVEMENT_REPORT)
    public ResponseEntity<byte[]> getStudentAchievementReport(@RequestBody Map<String, String> reportParameters) {
        logger.debug("Get Student Achievement Report");
        return reportService.getStudentAchievementReport(reportParameters);
    }

    @GetMapping (ReportApiConstants.STUDENT_TRANSCRIPT_REPORT)
    public ResponseEntity<byte[]> getStudentTranscriptReport(@RequestBody Map<String, String> reportParameters) {
        logger.debug("Get Student Achievement Report");
        return reportService.getStudentTranscriptReport(reportParameters);
    }
}

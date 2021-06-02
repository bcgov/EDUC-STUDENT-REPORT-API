package ca.bc.gov.educ.api.report;

import ca.bc.gov.educ.api.report.service.ReportService;
import ca.bc.gov.educ.grad.dto.GenerateReportRequest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class StudentReportApiApplicationTests extends GradReportBaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(StudentReportApiApplicationTests.class);
	private static final String CLASS_NAME = StudentReportApiApplicationTests.class.getSimpleName();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	ReportService reportService;

	GenerateReportRequest reportRequest;

	@Before
	public void init() throws Exception {
		reportRequest = createReportRequest("json/studentTranscriptReportRequest.json");
	}

	@Test
	public void createTranscriptReport() throws Exception {
		LOG.debug("<{}.createTranscriptReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		assertNotNull(reportRequest);
		ResponseEntity response = reportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/transcript.pdf")) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport");
	}

}

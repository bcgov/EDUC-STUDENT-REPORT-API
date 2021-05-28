package ca.bc.gov.educ.api.report;

import ca.bc.gov.educ.api.report.service.ReportService;
import ca.bc.gov.educ.grad.dto.GenerateReportRequest;
import ca.bc.gov.educ.grad.dto.ReportData;
import ca.bc.gov.educ.grad.dto.ReportOptions;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

class StudentReportApiApplicationTests extends GradReportBaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(StudentReportApiApplicationTests.class);
	private static final String CLASS_NAME = StudentReportApiApplicationTests.class.getSimpleName();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	ReportService reportService;

	GenerateReportRequest reportRequest;

	@Before
	public void init() throws Exception {
		super.init();
		initTranscriptReportRequest();
	}

	@Test
	public void createTranscriptReport() {
		LOG.debug("<{}.createTranscriptReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		assertNotNull(null);
		LOG.debug(">createCON11Report");
	}

	private void initTranscriptReportRequest() {
		reportRequest = new GenerateReportRequest();
		ReportOptions options = new ReportOptions("transcript");
		reportRequest.setOptions(options);

		ReportData data = new ReportData();
	}

}

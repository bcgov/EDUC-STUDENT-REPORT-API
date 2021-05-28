package ca.bc.gov.educ.api.report;


import ca.bc.gov.educ.api.report.service.utils.JsonTransformer;
import ca.bc.gov.educ.grad.dto.GenerateReportRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReportApiApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:application-test.yaml")
public abstract class GradReportBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(GradReportBaseTest.class);

    @Autowired
    JsonTransformer jsonTransformer;

    @BeforeClass
    public static void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Before
    public void init() throws Exception {

    }

    protected GenerateReportRequest createReportRequest(String jsonPath) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(jsonPath);
        String transcriptJson = readInputStream(inputStream);
        return (GenerateReportRequest)jsonTransformer.unmarshall(transcriptJson, GenerateReportRequest.class);
    }

    private String readInputStream(InputStream is) throws Exception {
        StringBuffer sb = new StringBuffer();
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}

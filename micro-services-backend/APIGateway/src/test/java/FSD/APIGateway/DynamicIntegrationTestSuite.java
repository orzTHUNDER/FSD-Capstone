package FSD.APIGateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DynamicIntegrationTestSuite {

        private static final Logger logger = LoggerFactory.getLogger(DynamicIntegrationTestSuite.class);

        @Autowired
        private TestRestTemplate restTemplate;

        private ObjectMapper objectMapper = new ObjectMapper();

        private List<TestCase> loadTestData() throws Exception {
                List<Map<String, Object>> data = objectMapper.readValue(new File("src/test/resources/test_data.json"),
                                List.class);
                List<TestCase> testCases = new ArrayList<>();
                for (Map<String, Object> map : data) {
                        String url = (String) map.get("url");
                        String requestBody = (String) map.get("request_body");
                        String method = (String) map.get("method");

                        logger.info("Testing URL: {}", url);
                        logger.info("Request Body: {}", requestBody);
                        int expectedStatus = (int) map.get("expected_status");
                        String expectedResponse = (String) map.get("expected_response");

                        logger.info("Response Status: {}", expectedStatus);
                        logger.info("Response Body: {}", expectedResponse);

                        testCases.add(new TestCase(url, requestBody, method, expectedStatus, expectedResponse));
                }
                return testCases;
        }

        @TestFactory
        Stream<DynamicTest> dynamicTests() throws Exception {
                return loadTestData().stream()
                                .map(testCase -> DynamicTest.dynamicTest("Testing " + testCase.getUrl(),
                                                () -> performTest(testCase)));
        }

        private void performTest(TestCase testCase) {
                String url = testCase.getUrl();
                String method = testCase.getMethod();
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type", "application/json");
                HttpEntity<String> request = new HttpEntity<>(testCase.getRequestBody(), headers);
                ResponseEntity<String> response;

                if (method.equals("GET")) {
                        response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
                } else if (method.equals("POST")) {
                        response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
                } else {
                        throw new UnsupportedOperationException("Unsupported HTTP method: " + method);
                }

                File logFile = new File("src/main/resources/test.log");
                if (logFile.exists()) {
                        logFile.delete();
                }

                logger.debug("Test Case: {}", testCase.getUrl());
                logger.debug("Request Body: {}", testCase.getRequestBody());
                logger.debug("Expected Status: {}", testCase.getExpectedStatus());
                logger.debug("Expected Response: {}", testCase.getExpectedResponse());
                logger.debug("Actual Status: {}", response.getStatusCode().value());
                logger.debug("Actual Response Body: {}", response.getBody());

                assertEquals(testCase.getExpectedStatus(), response.getStatusCode().value());
                assertEquals(testCase.getExpectedResponse(), response.getBody());
        }

        public static class TestCase {
                private final String url;
                private final String requestBody;
                private final String method;
                private final int expectedStatus;
                private final String expectedResponse;

                public TestCase(String url, String requestBody, String method, int expectedStatus,
                                String expectedResponse) {
                        this.url = url;
                        this.requestBody = requestBody;
                        this.method = method;
                        this.expectedStatus = expectedStatus;
                        this.expectedResponse = expectedResponse;
                }

                public String getUrl() {
                        return url;
                }

                public String getRequestBody() {
                        return requestBody;
                }

                public String getMethod() {
                        return method;
                }

                public int getExpectedStatus() {
                        return expectedStatus;
                }

                public String getExpectedResponse() {
                        return expectedResponse;
                }
        }
}

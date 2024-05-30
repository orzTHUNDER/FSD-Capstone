// package FSD.APIGateway;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.http.ResponseEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpMethod;

// @ExtendWith(SpringExtension.class)
// @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// public class GatewayIntegrationTest {

// @Autowired
// private TestRestTemplate restTemplate;

// @Test
// public void testValidSignUp() {

// String url = "/authentication/signup";

// String user = "{ \"password\": \"password123\",
// \"email\":\"testuser@example.com\" }";

// HttpHeaders headers = new HttpHeaders();
// headers.set("Content-Type", "application/json");

// // Create the HTTP request entity
// HttpEntity<String> request = new HttpEntity<>(user, headers);

// // Send the POST request to the API gateway
// ResponseEntity<String> responseEntity = restTemplate.exchange(url,
// HttpMethod.POST, request, String.class);

// System.out.println(responseEntity.getStatusCode());
// System.out.println(responseEntity.getBody());

// // Verify the response
// assertEquals(200, responseEntity.getStatusCode().value());
// assertEquals("Sign-Up Successfull!", responseEntity.getBody());

// }

// @Test
// public void testInvalidEmailSignup() {

// String url = "/authentication/signup";

// String user = "{ \"password\": \"password123\", \"email\":\"testuser\" }";

// HttpHeaders headers = new HttpHeaders();
// headers.set("Content-Type", "application/json");

// // Create the HTTP request entity
// HttpEntity<String> request = new HttpEntity<>(user, headers);

// // Send the POST request to the API gateway
// ResponseEntity<String> responseEntity = restTemplate.exchange(url,
// HttpMethod.POST, request, String.class);

// System.out.println(responseEntity.getStatusCode());
// System.out.println(responseEntity.getBody());

// // Verify the response
// assertEquals(404, responseEntity.getStatusCode().value());
// assertEquals("Sign-Up Successfull!", responseEntity.getBody());

// }

// }

package FSD.Authentication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.rsocket.server.LocalRSocketServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import FSD.Authentication.DAO.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
@Sql(scripts = "/schema.sql")
public class UserIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");

    @DynamicPropertySource
    static void dynamicConfiguration(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "com.mysql.cj.jdbc.Driver");
    }

    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    static void AfterAll() {
        mySQLContainer.stop();
    }

    // @Test
    // @Order(1)
    // public void testSignup() {
    // String signupUrl = "/signup"; // Assuming the signup endpoint is at /signup

    // // Create a User object to be signed up
    // User user = new User();
    // user.setUserName("testUser");
    // user.setPassword("testPassword");

    // // Set headers for JSON content
    // HttpHeaders headers = new HttpHeaders();
    // headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

    // // Create a request entity with user object and headers
    // HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

    // // Send the POST request and receive the response
    // ResponseEntity<String> responseEntity = restTemplate.exchange(signupUrl,
    // HttpMethod.POST, requestEntity,
    // String.class);

    // System.out.println(responseEntity.getStatusCode());
    // System.out.println(responseEntity.getBody());

    // // Verify response status code and body
    // assertEquals(200, responseEntity.getStatusCode().value()); // Assuming
    // success response code is 200
    // assertEquals("Sign-Up Successfull!", responseEntity.getBody()); // Assuming
    // success response body is "Sign-Up
    // // Successfull!"
    // }

    @BeforeEach
    public void testSignup() {
        String signupUrl = "/signup"; // Assuming the signup endpoint is at /signup

        // Create a User object to be signed up
        User user = new User();
        user.setEmail("testUser2@gmail.com");
        user.setPassword("testPassword");

        // Set headers for JSON content
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        // Create a request entity with user object and headers
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

        // Send the POST request and receive the response
        ResponseEntity<String> responseEntity = restTemplate.exchange(signupUrl, HttpMethod.POST, requestEntity,
                String.class);

        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());

        // Verify response status code and body
        assertEquals(200, responseEntity.getStatusCode().value()); // Assuming success response code is 200
        assertEquals("Sign-Up Successful!", responseEntity.getBody()); // Assuming success response body is "Sign-Up
                                                                       // Successfull!"
    }

    @Test
    @Order(2)
    public void testLogin() {
        String loginUrl = "/login"; // Assuming the login endpoint is at /login

        // Create a User object for login
        User user = new User();
        user.setEmail("testUser2@gmail.com");
        user.setPassword("testPassword");

        // Set headers for JSON content
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        // Create a request entity with user object and headers
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

        // Send the POST request and receive the response
        ResponseEntity<String> responseEntity = restTemplate.exchange(loginUrl, HttpMethod.POST, requestEntity,
                String.class);

        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());

        // Verify response status code and body
        assertEquals(200, responseEntity.getStatusCode().value()); // Assuming success response code is 200
        assertEquals("Login Successful!", responseEntity.getBody()); // Assuming success response body is "Login
                                                                     // Successfull!"
    }

    // Add more tests as needed
}
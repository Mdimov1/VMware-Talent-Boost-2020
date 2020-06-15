package org.vmware.finaltask.networkOfGiving;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vmware.finaltask.networkOfGiving.model.UserData;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
@ContextConfiguration
public class TestUserController {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRegisterNoExistingUser() {
        // Before
        final UserData user = new UserData(10, "TestUserName", "123456", "testName", 22, "male", "testCity");
        final String url = "/register";
        // When
        ResponseEntity<Void> response = restTemplate.postForEntity(url, user, Void.class);
        HttpStatus responseStatus = response.getStatusCode();

        // Then
        assertEquals(HttpStatus.OK, responseStatus);
    }

    @Test
    public void testRegisterExistingUserWithoutGender() {
        // Before
        final UserData user = new UserData(10, "TestUserName", "123456", "testName", 22,  "testCity");
        final String url = "/register";
        // When
        ResponseEntity<Void> response = restTemplate.postForEntity(url, user, Void.class);
        HttpStatus responseStatus = response.getStatusCode();

        // Then
        assertEquals(HttpStatus.OK, responseStatus);
    }

    @Test
    public void testRegisterExistingUserWithoutGenderAndLocation() {
        // Before
        final UserData user = new UserData(10, "TestUserName", "123456", "testName", 22);
        final String url = "/register";
        // When
        ResponseEntity<Void> response = restTemplate.postForEntity(url, user, Void.class);
        HttpStatus responseStatus = response.getStatusCode();

        // Then
        assertEquals(HttpStatus.OK, responseStatus);
    }

    @Test
    public void testRegisterExistingUser() {
        // Before
        final UserData user = new UserData(1, "TestUserName", "123456", "testName", 22, "male", "testCity");
        final String url = "/register";
        // When
        ResponseEntity<Void> response = restTemplate.postForEntity(url, user, Void.class);
        HttpStatus responseStatus = response.getStatusCode();

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseStatus);
    }

    @Test
    public void testGetExistingUserByValidUsernameWithNoAuthorization() {
        // Before
        final String username = "username1";
        final String url = "/user/" + username;

        // When
        ResponseEntity<UserData> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});
        HttpStatus responseStatus = response.getStatusCode();

        // Then
        assertEquals(HttpStatus.FORBIDDEN, responseStatus);
    }
}

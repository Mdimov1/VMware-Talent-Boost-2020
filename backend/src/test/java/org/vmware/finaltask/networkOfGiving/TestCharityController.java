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
import org.vmware.finaltask.networkOfGiving.model.Charity;

import java.util.List;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
@ContextConfiguration
public class TestCharityController {

    String baseURL = "/charity";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCountAndNotNullGetAllCharities() {
        // Before
        final int count_charities= 3;

        // When
        ResponseEntity<List<Charity>> response = restTemplate.exchange(baseURL, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});

        HttpStatus responseStatus = response.getStatusCode();
        List<Charity> charityList = response.getBody();

        // Then
        assertEquals(HttpStatus.OK, responseStatus);
        assertNotNull(charityList);
        assertEquals(count_charities, charityList.size());
    }

    @Test
    public void testGetValidCharityId(){
        // Before
        final int charity_Id = 1;
        final String url = baseURL + "/" + charity_Id;

        //When
        ResponseEntity<Charity> response = restTemplate.getForEntity(url, Charity.class);

        HttpStatus responseStatus = response.getStatusCode();
        Charity charity = response.getBody();

        //Then
        assertEquals(HttpStatus.OK, responseStatus);
        assertEquals(1, charity.getCharities_id());
    }

    @Test
    public void testGetNoValidCharityId(){
        // Before
        final int charity_Id = 0;
        final String url = baseURL + "/" + charity_Id;

        //When
        ResponseEntity<Charity> response = restTemplate.getForEntity(url, Charity.class);

        HttpStatus responseStatus = response.getStatusCode();

        //Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseStatus);
    }

    @Test
    public void testExistsValidCharityAuthor(){
        // Before
        final int charity_id = 1;
        final String url = baseURL + "/" + charity_id + "/author";
        String expectedAuthor = "name1";

        //When
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        HttpStatus responseStatus = response.getStatusCode();
        String charityAuthor = response.getBody();

        //Then
        assertEquals(HttpStatus.OK, responseStatus);
        assertEquals(expectedAuthor, charityAuthor);
    }

    @Test
    public void testGetValidCountParticipantsInCharity(){
        // Before
        final int charity_id = 1;
        final String url = baseURL + "/" + charity_id + "/participants";
        final Integer expectedCount = 2;

        //When
        ResponseEntity<Integer> response = restTemplate.getForEntity(url, Integer.class);
        HttpStatus responseStatus = response.getStatusCode();

        Integer charityParticipants = response.getBody();

        //Then
        assertEquals(HttpStatus.OK, responseStatus);
        assertEquals(expectedCount, charityParticipants);
    }

    @Test
    public void testGetValidDonatedAmountInCharity(){
        // Before
        final int charity_id = 1;
        final String url = baseURL + "/" + charity_id + "/donated";
        final Integer expectedAmount = 10;
        //When
        ResponseEntity<Integer> response = restTemplate.getForEntity(url, Integer.class);
        HttpStatus responseStatus = response.getStatusCode();

        Integer charityDonated = response.getBody();

        //Then
        assertEquals(HttpStatus.OK, responseStatus);
        assertEquals(expectedAmount, charityDonated);
    }

    @Test
    public void testDonateValidAmountInExistingCharity(){
        // Before
        final int charity_id = 1;
        final int amount = 10;
        final String url = baseURL + "/" + charity_id + "/donate";

        //When
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(url, amount, Void.class);
        HttpStatus responseStatus = responseEntity.getStatusCode();

        //Then
        assertEquals(HttpStatus.OK, responseStatus);
    }

    @Test
    public void testDonateInValidAmountInExistingCharity(){
        // Before
        final int charity_id = 1;
        final int amount = 0;
        final String url = baseURL + "/" + charity_id + "/donate";

        //When
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(url, amount, Void.class);
        HttpStatus responseStatus = responseEntity.getStatusCode();

        //Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseStatus);
    }


    @Test
    public void testDonateValidAmountInNoExistingCharity(){
        // Before
        int charity_id = 1000;
        int amount = 1;
        String url = baseURL + "/" + charity_id + "/donate";

        //When
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(url, amount, Void.class);
        HttpStatus responseStatus = responseEntity.getStatusCode();

        //Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseStatus);
    }

    @Test
    public void testAddNewValidCharityWithFullData(){
        // Before
        String url = baseURL + "/add";
        final Charity charity = new Charity("Test Charity", "testCharityImage.pgn", "Description test charity", 100, 100, 1);

        //When
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(url, charity, Void.class);
        HttpStatus responseStatus = responseEntity.getStatusCode();

        //Then
        assertEquals(HttpStatus.OK, responseStatus);
    }

    @Test
    public void testAddNewCharityWithFullDataWrongAuthorId(){
        // Before
        String url = baseURL + "/add";
        final Charity charity = new Charity("Test Charity", "testCharityImage.pgn", "Description test charity", 100, 100, 100);

        //When
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(url, charity, Void.class);
        HttpStatus responseStatus = responseEntity.getStatusCode();

        //Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseStatus);
    }

    @Test
    public void testAddNewCharityWithNegativeDonationRequired(){
        // Before
        String url = baseURL + "/add";
        final Charity charity = new Charity("Test Charity", "testCharityImage.pgn", "Description test charity", -100, 100, 100);

        //When
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(url, charity, Void.class);
        HttpStatus responseStatus = responseEntity.getStatusCode();

        //Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseStatus);
    }

    @Test
    public void testAddNewCharityWithNoParticipantsRequiredData(){
        // Before
        String url = baseURL + "/add";
        final Charity charity = new Charity("Test Charity", "testCharityImage.pgn", "Description test charity", 200, 2);

        //When
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(url, charity, Void.class);
        HttpStatus responseStatus = responseEntity.getStatusCode();

        //Then
        assertEquals(HttpStatus.OK, responseStatus);
    }

    @Test
    public void testAddNoParticipatingUserInExistingCharity(){
        // Before;
        final int charity_id = 1;
        final int user_id = 2;
        final String url = baseURL + "/" + charity_id + "/participants";

        //When
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(url, user_id, Void.class);
        HttpStatus responseStatus = responseEntity.getStatusCode();

        //Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseStatus);
    }

    @Test
    public void testAddAlreadyParticipatingUserInExistingCharity() {
        // Before;
        final int charity_id = 1;
        final int user_id = 1;
        final String url = baseURL + "/" + charity_id + "/participants";

        //When
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(url, user_id, Void.class);
        HttpStatus responseStatus = responseEntity.getStatusCode();

        //Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseStatus);
    }
}


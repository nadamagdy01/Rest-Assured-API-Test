package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestListOfBooksS3 {

    @Test
    public void testListBooks() {

        // Send a GET request to retrieve all bookings
        Response response = RestAssured
                .given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking");

        // Validate the response status code
        Assert.assertEquals(response.getStatusCode(), 200);

        // Validate that the list of bookings is not empty
        Assert.assertTrue(response.jsonPath().getList("").size() > 0, "Book list is empty");
    }
}


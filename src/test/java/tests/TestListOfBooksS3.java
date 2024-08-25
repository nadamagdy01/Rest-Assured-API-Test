package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestListOfBooksS3 {

    @Test
    public void testListBooks() {

        Response response = RestAssured
                .given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getList("").size() > 0, "Book list is empty");
    }
}


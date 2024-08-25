package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.json.JSONObject;

public class TestResponseS1 {


    @Test
    public void testAuthTokenGeneration() {

        // Create a RequestSpecification object
        RequestSpecification request = RestAssured.given();

        // Set the base URI for API EndPoint
        request.baseUri("https://restful-booker.herokuapp.com/auth");

        // Set the content type of the request to JSON
        request.header("Content-Type", "application/json");

        // The request body with authentication credentials
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "admin");
        requestParams.put("password", "password123");

        // Send a POST request to authenticate and generate a token
        Response response = request.body(requestParams.toString()).post();

        // Validate the response status code
        Assert.assertEquals(response.getStatusCode(), 200);

        // Validate that the token is not empty
        Assert.assertNotNull(response.jsonPath().getString("token"));
    }
}


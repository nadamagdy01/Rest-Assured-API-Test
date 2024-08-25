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

        RequestSpecification request = RestAssured.given();
        request.baseUri("https://restful-booker.herokuapp.com/auth");
        request.header("Content-Type", "application/json");


        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "admin");
        requestParams.put("password", "password123");


        Response response = request.body(requestParams.toString()).post();
        System.out.println("Response: "+ response);


        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("token"));
    }
}


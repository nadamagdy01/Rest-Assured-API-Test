package tests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.json.JSONObject;

public class TestAddedBookS2 {

    @Test
    public void testCreateBook() {

        // Create a RequestSpecification object
        RequestSpecification request = RestAssured.given();

        // Set the base URI for the API endpoint
        request.baseUri("https://restful-booker.herokuapp.com/booking");

        // Set the content type of the request to JSON
        request.header("Content-Type", "application/json");

        // The request body with the booking details
        JSONObject requestParams = new JSONObject();
        requestParams.put("firstname", "Nada");
        requestParams.put("lastname", "Magdy");
        requestParams.put("totalprice", 111);
        requestParams.put("depositpaid", true);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");
        requestParams.put("bookingdates", bookingDates);
        requestParams.put("additionalneeds", "Breakfast");

        // Send a POST request
        Response response = request.body(requestParams.toString()).post();

        // Print the response body
        System.out.println("Response Body: " + response.getBody().asPrettyString());

        // Validate the response status and the booking details
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("booking.firstname"), "Nada");
        Assert.assertEquals(response.jsonPath().getString("booking.lastname"), "Magdy");
        Assert.assertEquals(response.jsonPath().getInt("booking.totalprice"), 111);
    }
}



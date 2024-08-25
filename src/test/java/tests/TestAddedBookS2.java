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

        RequestSpecification request = RestAssured.given();
        request.baseUri("https://restful-booker.herokuapp.com/booking");
        request.header("Content-Type", "application/json");


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


        Response response = request.body(requestParams.toString()).post();


        System.out.println("Response Body: " + response.getBody().asPrettyString());


        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("booking.firstname"), "Nada");
        Assert.assertEquals(response.jsonPath().getString("booking.lastname"), "Magdy");
        Assert.assertEquals(response.jsonPath().getInt("booking.totalprice"), 111);
    }
}



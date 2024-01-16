package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PatchPartialBookingTest extends TestBase {

    @Test
    public void updateARecordPartially() {
        BookingPojo.BookingDates bookingDates = new BookingPojo.BookingDates();
        bookingDates.put("checkIn", "2024-03-04");
        bookingDates.put("checkOut", "2025-04-04");
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setBookingdates(bookingDates);
        bookingPojo.setFirstname("Ash");
        bookingPojo.setLastname("Kakad");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .header("Accept", "application/json")
                .pathParam("id", 10)
                .body(bookingPojo)
                .when().patch("https://restful-booker.herokuapp.com/booking/{id}");
        response.then().log().all().statusCode(200);
        response.prettyPrint();
    }

}

package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostBookingTest extends TestBase {

    @Test
    public void verifyBookingCreatedSuccessfully() {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname("Asha");
        bookingPojo.setLastname("Kakadiya");
        bookingPojo.setTotalPrice(555);
        bookingPojo.setDepositPaid(true);
        BookingPojo.BookingDates bookingDates = new BookingPojo.BookingDates();
        bookingDates.put("checkIn", "2024-03-04");
        bookingDates.put("checkOut", "2025-04-04");
        bookingPojo.setAdditionalNeeds("Dinner");
        bookingPojo.setBookingdates(bookingDates);

        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .header("Accept", "application/json")
                .when()
                .body(bookingPojo)
                .post("/booking");
        response.then().log().all().statusCode(404);
        response.prettyPrint();

    }

}

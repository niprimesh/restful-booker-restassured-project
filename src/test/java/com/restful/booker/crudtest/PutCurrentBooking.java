package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutCurrentBooking extends TestBase {
    @Test
    public void updateCurrentBooking() {
        BookingPojo.BookingDates date = new BookingPojo.BookingDates();
        date.setCheckin("2023-06-01");
        date.setCheckout("2023-06-05");
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname("Krishna");
        bookingPojo.setLastname("Patel");
        bookingPojo.setTotalPrice(350);
        bookingPojo.setDepositPaid(true);
        bookingPojo.setBookingdates(date);
        bookingPojo.setAdditionalNeeds("Dinner");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .header("Accept", "application/json")
                .pathParam("id", 125)
                .body(bookingPojo)
                .when()
                .put("/booking/{id}");
        response.then().statusCode(404);
        response.prettyPrint();
    }

}


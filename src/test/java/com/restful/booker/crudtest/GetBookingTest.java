package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetBookingTest extends TestBase {
    static String firstname = TestUtils.getRandomValue();
    static String lastname = TestUtils.getRandomValue();

    @Test
    public void getAllBookingIds() {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        List<String> bookingdates = new ArrayList<>();
        bookingdates.add("2018-01-01");
        bookingdates.add("2019-01-01");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .header("Accept", "application/json")
                .body(bookingPojo)
                .when().get("https://restful-booker.herokuapp.com/booking");
        response.then().log().all().statusCode(200);
    }

    @Test
    public void getBookingIds() {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        List<String> bookingdates = new ArrayList<>();
        bookingdates.add("2018-01-01");
        bookingdates.add("2019-01-01");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .header("Accept", "application/json")
                .pathParam("id", 10)
                .body(bookingPojo)
                .when().get("https://restful-booker.herokuapp.com/booking/{id}");
        response.then().log().all().statusCode(200);
    }

    @Test
    public void getBookingWithFirstName() {
        BookingPojo bookingPojo = new BookingPojo();
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .header("Accept", "application/json")
                .body(bookingPojo)
                .param("firstname", firstname)
                .when().get("https://restful-booker.herokuapp.com/booking");
        response.then().log().all().statusCode(200);
    }

}



package com.restful.booker.crudtest;

import com.restful.booker.model.AuthPojo;
import com.restful.booker.model.BookingPojo;
import com.restful.booker.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CRUDTest {
    static String username = "admin";
    static String password = "password123";
    static String token;
    @Test
    public void createToken() {
        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername(username);
        authPojo.setPassword(password);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(authPojo)
                .post("https://restful-booker.herokuapp.com/auth");
        response.then().log().all().statusCode(200);
        String jsonString = response.asString();
        token = JsonPath.from(jsonString).get("token");
        System.out.println("Token is: " + token);
    }
    @Test
    public void postBookingTest() {
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

    @Test
    public void patchBookingTest() {
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

    @Test
    public void putBookingTest() {
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

    @Test
    public void deleteBookingTest() {
        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .delete("https://restful-booker.herokuapp.com/booking/4");

        response.then().log().all().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void healthCheckTest(){
        Response response = given().log().all()
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .get("https://restful-booker.herokuapp.com/ping");
        response.then().statusCode(201);
        response.prettyPrint();
    }

}

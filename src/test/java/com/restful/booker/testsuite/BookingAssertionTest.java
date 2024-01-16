package com.restful.booker.testsuite;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookingAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        response = given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/704")
                .then().statusCode(200);
    }

    // Verify first name john
    @Test
    public void test01(){
        System.out.println("The First name value is : " + response.extract().path("firstname"));
    }
    // Verify checkin is on 2018-01-01
    @Test
    public void test002(){
        response.body("bookingdates.checkin", equalTo("2018-01-01"));
    }
    //Verify totalprice is greater than 100
    @Test
    public void test003()
    {
        response.body("totalprice", greaterThan(100));
    }
    //Verify that the lastname is "Smith"
    @Test
    public void test004(){
        response.body("lastname", equalTo("Smith"));
    }

    // Verify that the additionalneeds is Breakfast
    @Test
    public void test005(){
        response.body("additionalneeds", equalTo("Breakfast"));
    }
    // Verify checkout is on 2019-01-01
    @Test
    public void test006() {
        response.body("bookingdates.checkout", equalTo("2019-01-01"));
    }

    // Verify that the additionalneeds is Breakfast
    @Test
    public void test007() {
        response.body("additionalneeds", equalTo("Breakfast"));
    }
}

package com.restful.booker.crudtest;

import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteBookingTest extends TestBase {
        @Test
        public void verifyBookingDeletedSuccessfully() {
            Response response = given().log().all()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                    .when()
                    .delete("https://restful-booker.herokuapp.com/booking/2");

            response.then().log().all().statusCode(201);
            response.prettyPrint();
        }
    }

package com.restful.booker.crudtest;

import com.restful.booker.model.AuthPojo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthCRUDTest {
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
    }

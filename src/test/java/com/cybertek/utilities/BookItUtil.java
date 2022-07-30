package com.cybertek.utilities;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BookItUtil {
    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";

    }


    public String finalToken(String email, String password){

         String token= given().queryParam("email",email)
                 .and()
                .get("/sign")
                .then()
                .extract().jsonPath().getString("accessToken");

        return "Bearer "+token;

    }


}

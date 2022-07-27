package com.cybertek.day7;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PutAndPatchRequestDemo extends SpartanTestBase {

    @DisplayName("PUT request to one spartan for update with Map")
    @Test
    public void PUTRequest(){
        //just like post request we have different options to send body, we will go with map
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name","BruceWayne");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone",8811111111L);

        given().contentType(ContentType.JSON) //hey api I am sending JSON body
                .body(putRequestMap).log().body()
                .and().pathParam("id",114)
                .when().put("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send

        given().accept(ContentType.JSON)
                .and().pathParam("id", 114)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .body("id",is(114),
                        "name", is("BruceWayne"),
                        "gender",is("Male"),
                        "phone",is(8811111111L))
                .log().all();





    }

    @DisplayName("PATCH request to one spartan for partial update with Map")
    @Test
    public void PATCHRequest() {
        //just like post request we have different options to send body, we will go with map

        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone",8811111111L);

        given().contentType(ContentType.JSON) //hey api I am sending JSON body
                .body(putRequestMap).log().body()
                .and().pathParam("id",115)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);




    }
    @DisplayName("DELETE one spartan")
    @Test
    public void deleteSpartan(){
        given().pathParam("id", 115)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);
        //send a get request after you delete make sure you are getting 404

        given().pathParam("id",115)
                .when().get("/api/spartans/{id}")
                .then().statusCode(404);

    }




}

package com.cybertek.day12;

import com.cybertek.utilities.SpartanNewBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.restassured.filter.log.LogDetail;


public class OldRestAssuredTest extends SpartanNewBase
{
@Test
    public void getAllSpartan(){
    given()
            .accept(ContentType.JSON)
            .and()
            .auth().basic("admin","admin")
    .when()
            .get("/spartans")
    .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id[0]",is(1))
            .log().all();

}

    /*
              in previous version of Restassured, the given when then style
              was actually written in given expect when format.
              it will assert all the result and give one answer and does not fail whole thing
              if first one fail unlike new structure.
           */
    @Test
    public void Test2() {
        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .log().all()
        .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]",is(1))
                .logDetail(LogDetail.ALL) //log way using with expect
                .when()
                .get("/spartans");
    }

}

package com.cybertek.day8;

import com.cybertek.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanAdminCRUD extends SpartanAuthTestBase {

    @DisplayName("POST/api/spartans as admin user expect 201")
    @Test
    public void testAdminPOST() {
        String requestJsonBody= " {\"gender\":\"Male\",\n" +
                "      \"name\":\"Strange\",\n" +
                "      \"phone\":8877445544\n" +
                "   }";

        Response response = given()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(requestJsonBody)
                .when()
                .post("/api/spartans")
                .then().statusCode(201)
                .extract().response();
        response.prettyPrint();


    }


    @DisplayName("GET /api/spartans as admin user expect 200")
    @Test
    public void testAdminGET() {
        //how to pass admin admin as a username and password ?
        given()
                .auth().basic("admin","admin")
                .and().accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then().statusCode(200)
                .log().all();

    }
    @DisplayName("PUT/api/spartans/{id} as admin user expect 204")
    @Test
    public void testAdminPUT() {
        String requestJsonBody= " {\"gender\":\"Male\",\n" +
                "      \"name\":\"DrStrange\",\n" +
                "      \"phone\":8877445544\n" +
                "   }";

        Response response = given().header("Content-Type", "application/json")
                .auth().basic("admin", "admin")
                .body(requestJsonBody).pathParam("id", "101")
                .put("/api/spartans/{id}")
                .then().statusCode(204)
                .extract().response();

        response.prettyPrint();


    }
    @DisplayName("PATCH/api/spartans/{id} as admin user expect 204")
    @Test
    public void testAdminPATCH() {
        String request = "{\n" +
                "  \"name\": \"Monte Kristo\"\n" +
                "}";

        Response response = given().header("Content-Type", "application/json")
                .auth().basic("admin", "admin")
                .body(request).pathParam("id", "70")
                .patch("/api/spartans/{id}")
                .then().statusCode(204)
                .extract().response();

        response.prettyPrint();
    }




    @DisplayName("DELETE/api/spartans/{id} as admin user expect 204")
    @Test
    public void testAdminDELETE() {


        Response response= given()
                .auth().basic("admin", "admin")
                .pathParam("id", "101")
                .delete("/api/spartans/{id}")
                .then().statusCode(204)
                .extract().response();

        response.prettyPrint();



    }





}

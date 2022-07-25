package com.cybertek.apiReview;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class ZipPathTest extends ZipBase {
    @Test
    public void pathTest()
    {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when().get("/{zip}");
        response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals("application/json",response.contentType());

        assertTrue(response.header("Server").equalsIgnoreCase("cloudflare"));
        assertTrue(response.headers().hasHeaderWithName("Report-To"));

        assertEquals("22031", response.path("\'post code\'"));
        assertEquals("US",response.path("\'country abbreviation\'"));
        assertEquals("Fairfax", response.path("places.\'place name\'[0]"));

        assertEquals("Virginia",response.path("places[0].state"));
        assertEquals("38.8604",response.path("places[0].latitude"));

//JSONPATH
        JsonPath jsonPathForResponse = response.jsonPath();
        assertEquals("United States",jsonPathForResponse.getString("country"));
    }

}

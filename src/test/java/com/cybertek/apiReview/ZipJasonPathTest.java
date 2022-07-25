package com.cybertek.apiReview;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ZipJasonPathTest extends ZipBase {

    @Test
            public void test1(){
        Response response=given().accept(ContentType.JSON)
                .and().pathParam("state", "ma")
                .and().pathParam("city","belmont")

                .when().get("/{state}/{city}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.header("Server").equalsIgnoreCase("cloudflare"));
        assertTrue(response.headers().hasHeaderWithName("Report-To"));

        JsonPath jsonPath=response.jsonPath();
        assertEquals("United States", jsonPath.getString("country"));
        List<String> expectedZips = new ArrayList<>(Arrays.asList("02178","02478"));
        List<String> actualZips = jsonPath.getList("places.\'post code\'");
        assertEquals(expectedZips,actualZips);

        assertEquals("Massachusetts", jsonPath.getString("state"));
        assertEquals("Belmont",jsonPath.getString("\'place name\'"));
        assertEquals("Massachusetts", jsonPath.getString("state"));

        List<String> expectedLatitudes= new ArrayList<>(Arrays.asList("42.4464","42.4128"));
        List<String> actualLatitudes = jsonPath.getList("places.\'latitude\'");
        assertEquals(expectedLatitudes,actualLatitudes);

        String var=jsonPath.getString("places.findAll {it.\'post code\'==\"02178\"}.latitude");


    }



      /*
    Exercise with JsonPath Method
Given Accept application/json
And "city" path is ma/belmont
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
   post codes are existing : "02178","02478"
   country  is United States
   state abbreviation is MA
   place name is Belmont
   state is Massachusetts
   latitudes are 42.4464,42.4128
    */
}

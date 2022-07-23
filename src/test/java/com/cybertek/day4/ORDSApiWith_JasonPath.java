package com.cybertek.day4;
import com.cybertek.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiWith_JasonPath extends HRTestBase {

        @DisplayName("GET request to countries")
        @Test
        public void test1(){

                Response response= get("/countries");

                //get the second country name with JsonPath

                //to use jsonpath we assign response to JsonPath

                JsonPath jsonPath= response.jsonPath();
                String secondCountryName = jsonPath.getString("items[1].country_name");

                //get all country ids
                //items.country_id
                List<String> allCountryIDs= jsonPath.getList("items.country_id");
                System.out.println("allCountryIDs = " + allCountryIDs);


        }


}

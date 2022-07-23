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

                //get all country names where their region id is equal to 2

                List<String> countryNameWithRegionID2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
                System.out.println("countryNameWithRegionID2 = " + countryNameWithRegionID2);

        }
        @DisplayName("GET requesto /employees with query param")
        @Test
        public void test2() {
                //we added limit query param to get 107 employees
                Response response = given().queryParam("limit",107)
                        .when().get("/employees");

                //get me all e-mail of employees who is working as IT_PROG
                JsonPath jsonPath=response.jsonPath();
                List<String> employeeITProgs = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
                System.out.println("employeeITProgs = " + employeeITProgs);

                //get me first name of all employees who is making more than 10000

                List<Integer> employeeSalariesMoreThan10000=jsonPath.getList("items.findAll {it.salary>10000}.first_name");
                System.out.println("employeeSalariesMoreThan10000 = " + employeeSalariesMoreThan10000);

        }


}

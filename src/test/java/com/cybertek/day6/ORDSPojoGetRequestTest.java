package com.cybertek.day6;

import com.cybertek.pojo.Link;
import com.cybertek.pojo.Region;

import com.cybertek.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



public class ORDSPojoGetRequestTest extends HRTestBase{
    @Test
    public void regionTest(){

        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();
        Region region1= jsonPath.getObject("items[0]", Region.class);

        System.out.println("region1 = " + region1);

    }

}

package com.cybertek.day2;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class HrGetRequests {

    //BeforeAll is annotation equals to @BeforeClass in testNG, we use with static method name
    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://44.202.105.211:1000/ords/hr";


    }
}

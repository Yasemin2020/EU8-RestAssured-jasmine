package com.cybertek.utilities;

import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.baseURI;



public class HRTestBase {


        @BeforeAll
        public static void init(){
            //save baseURI inside this variable so that we dont need to type each http method
            baseURI="http://44.202.105.211:1000/ords/hr";

            //get ip adress from configuration

        }

}

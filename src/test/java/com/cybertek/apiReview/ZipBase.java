package com.cybertek.apiReview;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ZipBase {
@BeforeAll
    public static void init(){
    baseURI="http://api.zippopotam.us";
    basePath="/us";
}

}

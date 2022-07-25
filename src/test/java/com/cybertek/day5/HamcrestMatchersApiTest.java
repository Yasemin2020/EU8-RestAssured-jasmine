package com.cybertek.day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

    public class HamcrestMatchersApiTest {
    /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

        @DisplayName("OneSpartan with Hamcrest and chaining")
        @Test
        public void test1(){
            given().log().all()//for request part
                    .accept(ContentType.JSON)
                    .and().pathParam("id",15)
            .when()
                    .get("http://44.202.105.211:8000/api/spartans/{id}")
            .then().assertThat()
                    .statusCode(200)
                    .and()
                    .body("id",equalTo(15),
            "name",is("Meta"),
                            "gender",is("Female"),
                            "phone",is(1938695106))
                    .log().all();//for request part



        }
        /*
         "teacherId": 21887,
            "firstName": "",
            "lastName": "Messi",
            "emailAddress": "LeonelMessi@Gmail.com",
            "joinDate": "09/09/2009",
            "password": "4545454545",
            "phone": "332-569-2488",
            "subject": "IT",
            "gender": "Male",
            "department": "Trucking",
            "birthDate": "06/04/1987",
            "salary": 2000,
            "batch": 14,
            "section": "OK",
            "premanentAddress": "2424 W Oregon street, Des Plaines, IL 60016"


         */
        @Test
        public void teacherData(){
            given()
                    .accept(ContentType.JSON)
                    .and()
                    .pathParam("id",21887)
            .when()
                    .get("http://api.cybertektraining.com/teacher/{id}")
            .then()
                    .statusCode(200)
                    .contentType("application/json;charset=UTF-8")
                    .header("Content-Length",is("275"))
                    .assertThat()
                    .body("teachers[0].firstName",is("Leonel"))
                    .body("teachers[0].lastName",is("Messi"))
                    .body("teachers[0].gender",equalTo("Male"));

        }


        @DisplayName("GET request to teacher/all and chaining")
        @Test
        public void teachersTest(){
            //verify Alexander,Darleen,Sean inside the all teachers
            given()
                    .accept(ContentType.JSON)
                    .when()
                    .get("http://api.cybertektraining.com/teacher/all")
                    .then()
                    .statusCode(200)
                    .and()
                    .body("teachers.firstName",hasItems("Alexander","Dorsey","James","Mohammad"));


        }


        }



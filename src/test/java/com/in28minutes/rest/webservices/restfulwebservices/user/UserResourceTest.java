package com.in28minutes.rest.webservices.restfulwebservices.user;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserResourceTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void testRetrieveUserByParam_200() {
        given()
                .queryParam("param", "7amok4a")
                .when()
                .get("/users/query")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void testRetrieveAllUsers_200() {
//        Response empResponse =
        given()
                .when()
                .get("/users")
                .then()
                .log()
                .all()
                .assertThat().
                statusCode(200)
                .and()
//                .body("$", hasSize())
                .body("[0].id", equalTo(1))
                .body("[0].name", equalTo("Adam"));
    }

    @Test
    void testSaveUser_201() throws JSONException, ParseException, IOException {

        String jsonBody = new String(Files.readAllBytes(Paths.get("src/test/resources/post-body.json")));

        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("/users")
                .then()
                .assertThat().
                statusCode(201);
    }
}
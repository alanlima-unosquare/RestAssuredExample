package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class PostTests {

    public static final String URI = "https://reqres.in/api/";

    @BeforeClass
    public void setBase() {
        RestAssured.baseURI = URI;
    }

    @Test
    public void validateNewUser() throws ParseException, IOException {
        this.sendPost("newUser.json");
    }

    @Test
    public void validateLogin() throws ParseException, IOException {
        this.sendPost("existingUser.json");
    }

    private void sendPost(String jsonFileName) throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(new FileReader(jsonFileName));
        String jsonBody = json.toString();
        String resource = "users";
        Response response = given()
                .when()
                .headers("Content-Type", "application/json")
                .body(jsonBody)
                .post(resource);
        Reporter.log(String.format("Response body:\n%s\n", response.body().asString()));
        Reporter.log(String.format("Response code:\n%d\n", response.statusCode()));
        Reporter.log(String.format("JSON body:\n%s\n", jsonBody));
        Reporter.log(String.format("URL:\n%s\n", URI + resource));
        response.then()
                .assertThat()
                .statusCode(equalTo(201));
    }
}

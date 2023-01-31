package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;

import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class APICore {
    public static final String URI = "https://reqres.in/api/";

    public APICore() {
        RestAssured.baseURI = APICore.URI;
    }

    Response sendPost(String jsonFileName, String resource) throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(new FileReader(jsonFileName));
        String jsonBody = json.toString();
        Response response = given()
                .when()
                .headers("Content-Type", "application/json")
                .body(jsonBody)
                .post(resource);
        Reporter.log(String.format("Response body:\n%s\n", response.body().asString()));
        Reporter.log(String.format("Response code:\n%d\n", response.statusCode()));
        Reporter.log(String.format("JSON body:\n%s\n", jsonBody));
        Reporter.log(String.format("URL:\n%s\n", URI + resource));
        return response;
    }
}

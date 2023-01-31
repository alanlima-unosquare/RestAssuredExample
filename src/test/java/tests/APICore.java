package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
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

    Response post(String jsonFileName, String resource) throws ParseException, IOException {
        return postOrPut(jsonFileName, resource, PostOrPut.POST);
    }

    Response put(String jsonFileName, String resource) throws ParseException, IOException {
        return postOrPut(jsonFileName, resource, PostOrPut.PUT);
    }

    Response postOrPut(String jsonFileName, String resource, PostOrPut postOrPut) throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(new FileReader(jsonFileName));
        String jsonBody = json.toString();
        RequestSpecification requestSpecification = given()
                .when()
                .headers("Content-Type", "application/json")
                .body(jsonBody);
        Response response = switch (postOrPut) {
            case POST -> requestSpecification.post(resource);
            case PUT -> requestSpecification.put(resource);
        };
        Reporter.log(String.format("Response body:\n%s\n", response.body().asString()));
        Reporter.log(String.format("Response code:\n%d\n", response.statusCode()));
        Reporter.log(String.format("JSON body:\n%s\n", jsonBody));
        Reporter.log(String.format("URL:\n%s\n", URI + resource));
        return response;
    }

    Response get(String resource) {
        Response response = given()
                .when()
                .get(resource);
        Reporter.log(String.format("Response body:\n%s\n", response.body().asString()));
        Reporter.log(String.format("Response code:\n%d\n", response.statusCode()));
        Reporter.log(String.format("URL:\n%s\n", URI + resource));
        return response;
    }
}

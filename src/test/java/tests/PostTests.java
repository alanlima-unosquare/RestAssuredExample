package tests;

import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;


public class PostTests {


    private APICore apiCore;

    @BeforeClass
    public void setBase() {
        apiCore = new APICore();
    }

    @Test
    public void validateNewUser() throws ParseException, IOException {
        Response response = apiCore.sendPost("newUser.json", "users");
        response.then()
                .assertThat()
                .statusCode(equalTo(201));
    }

    @Test
    public void validateLogin() throws ParseException, IOException {
        Response response = apiCore.sendPost("existingUser.json", "unknown");
        response.then()
                .assertThat()
                .statusCode(equalTo(201));
    }
}

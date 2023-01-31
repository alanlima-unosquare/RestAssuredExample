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
        Response response = apiCore.post("newUser.json", "users");
        response.then()
                .assertThat()
                .statusCode(equalTo(201));
    }

    @Test
    public void validateLogin() throws ParseException, IOException {
        Response response = apiCore.post("existingUser.json", "unknown");
        response.then()
                .assertThat()
                .statusCode(equalTo(201));
    }

    @Test
    public void validateRegister() throws ParseException, IOException {
        Response response = apiCore.post("registerUser.json", "register");
        response.then()
                .assertThat()
                .statusCode(equalTo(200));
    }

    @Test
    public void validateRegisterUnsuccessful() throws ParseException, IOException {
        Response response = apiCore.post("registerUserUnsuccessful.json", "register");
        response.then()
                .assertThat()
                .statusCode(equalTo(400));
    }
}

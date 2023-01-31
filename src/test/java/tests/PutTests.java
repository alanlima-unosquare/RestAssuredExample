package tests;

import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;

public class PutTests {
    private APICore apiCore;

    @BeforeClass
    public void setBase() {
        apiCore = new APICore();
    }

    @Test
    public void validateUpdateUser() throws ParseException, IOException {
        Response response = apiCore.put("updateUser.json", "users/2");
        response.then()
                .assertThat()
                .statusCode(equalTo(200));
    }
}

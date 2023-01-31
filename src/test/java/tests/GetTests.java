package tests;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetTests {


    private APICore apiCore;

    @BeforeClass
    public void setBase() {
        apiCore = new APICore();
    }

    @Test
    public void validateListUsers() {
        Response response = apiCore.get("users");
        response.then().statusCode(200);
    }

    @Test
    public void validateListOneUser() {
        Response response = apiCore.get("users/1");
        response.then().statusCode(200);
    }

    @Test
    public void validateListUnknowns() {
        Response response = apiCore.get("unknown");
        response.then().statusCode(200);
    }

    @Test
    public void validateListOneUnknown() {
        Response response = apiCore.get("unknown/1");
        response.then().statusCode(200);
    }
}

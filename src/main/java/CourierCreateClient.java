import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class CourierCreateClient extends ApiClient {

    private String baseURI = "api/v1/courier";

    @Step("Регистрация курьера")
    public Response courierAdd(Courier courier){
        return given()
                .spec(getBaseSpec())
                .and()
                .body(courier)
                .when()
                .post(baseURI);
    }
}


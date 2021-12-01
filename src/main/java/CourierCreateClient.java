import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class CourierCreateClient extends ApiClient {

    private String baseURI = "api/v1/courier";
    Response courierAddResponse;

    @DisplayName("Регистрация курьера")
    public void courierAdd(Courier courier){
        Response response =  given()
                .spec(getBaseSpec())
                .and()
                .body(courier)
                .when()
                .post(baseURI);
        courierAddResponse = response;
    }
}


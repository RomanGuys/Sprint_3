import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CourierLoginClient extends ApiClient {
    private String baseURI = "api/v1/courier/login";

    @Step("Логин курьера")
    public Response courierLogin(CourierCredentials courierCredentials){
        return given()
                .spec(getBaseSpec())
                .and()
                .body(courierCredentials)
                .when()
                .post(baseURI);
    }

    @Step("Получение айди курьера")
    public int getCourierId(CourierCredentials courierCredentials){
        int courierId = 0;
        Response response = given()
                .spec(getBaseSpec())
                .and()
                .body(courierCredentials)
                .when()
                .post(baseURI);

        if (response.statusCode() == 200) {
            JsonPath path = response.jsonPath();
            courierId = path.get("id");
        }
        return courierId;
    }
}

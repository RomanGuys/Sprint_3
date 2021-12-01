import io.qameta.allure.junit4.DisplayName;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.ArrayList;
import static io.restassured.RestAssured.given;

public class CourierLoginClient extends ApiClient {
    private String baseURI = "api/v1/courier/login";
    Response courierLoginResponse;

    public void courierLogin(CourierCredentials courierCredentials){
//        String courierLogin = loginData.get(0);
//        String courierPassword = loginData.get(1);
//        String body = "{\"login\" :\"" + courierLogin + "\" ,\"password\" : \"" + courierPassword + "\"}";
        Response response = given()
                .spec(getBaseSpec())
                .and()
                .body(courierCredentials)
                .when()
                .post(baseURI);

        courierLoginResponse = response;
    }

    @DisplayName("Получение айди курьера")
    public int getCourierId(Courier courier){
        int courierId = 0;
        String login = courier.login;
        String pass = courier.password;
        String loginRequestBody = "{\"login\":\"" + login + "\","
                + "\"password\":\"" + pass + "\"}";

        Response response = given()
                .spec(getBaseSpec())
                .and()
                .body(loginRequestBody)
                .when()
                .post(baseURI);

        if (response.statusCode() == 200) {
            JsonPath path = response.jsonPath();
            courierId = path.get("id");
        }
        return courierId;
    }
}

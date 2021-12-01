import io.qameta.allure.junit4.DisplayName;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.ArrayList;
import static io.restassured.RestAssured.given;

public class CourierLoginClient {
    private String baseURI = "https://qa-scooter.praktikum-services.ru/api/v1/courier";
    Response courierLoginResponse;

    public void courierLogin(ArrayList<String> loginData){
        String courierLogin = loginData.get(0);
        String courierPassword = loginData.get(1);
        String body = "{\"login\" :\"" + courierLogin + "\" ,\"password\" : \"" + courierPassword + "\"}";
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(baseURI + "/login");

        courierLoginResponse = response;
    }

    @DisplayName("Получение айди курьера")
    public int getCourierId(ArrayList<String> loginPass){
        int courierId = 0;
        String login = loginPass.get(0);
        String pass = loginPass.get(1);
        String loginRequestBody = "{\"login\":\"" + login + "\","
                + "\"password\":\"" + pass + "\"}";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(loginRequestBody)
                .when()
                .post(baseURI + "/login");

        if (response.statusCode() == 200) {
            JsonPath path = response.jsonPath();
            courierId = path.get("id");
        }
        return courierId;
    }
}

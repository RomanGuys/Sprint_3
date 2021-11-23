import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class CourierLoginTestMethods {
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
}

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import static io.restassured.RestAssured.*;

public class CourierCreateClient extends ApiClient {

    private String baseURI = "api/v1/courier";
    Response courierAddResponse;

    public void courierAdd(Courier courier){
//        String courierLogin = courierData.get(0);
//        String courierPassword = courierData.get(1);
//        String courierFirstName = courierData.get(2);
//        String registerRequestBody = "{\"login\":\"" + courierLogin + "\","
//                + "\"password\":\"" + courierPassword + "\","
//                + "\"firstName\":\"" + courierFirstName + "\"}";

        Response response =  given()
                .spec(getBaseSpec())
                .and()
                .body(courier)
                .when()
                .post(baseURI);
        courierAddResponse = response;
    }

    @DisplayName("Регистрация рандомного курьера")
    public ArrayList<String> registerNewCourierAndReturnLoginPassword(){

        String courierLogin = RandomStringUtils.randomAlphabetic(10);
        String courierPassword = RandomStringUtils.randomAlphabetic(10);
        String courierFirstName = RandomStringUtils.randomAlphabetic(10);
//
        ArrayList<String> loginPass = new ArrayList<>();

        String registerRequestBody = "{\"login\":\"" + courierLogin + "\","
                + "\"password\":\"" + courierPassword + "\","
                + "\"firstName\":\"" + courierFirstName + "\"}";

        Response response =  given()
                .spec(getBaseSpec())
                .and()
                .body(registerRequestBody)
                .when()
                .post(baseURI);

        // если регистрация прошла успешно (код ответа 201), добавляем в список логин и пароль курьера
        if (response.statusCode() == 201) {
            loginPass.add(courierLogin);
            loginPass.add(courierPassword);
        }

        // возвращаем список
        return loginPass;

    }

}
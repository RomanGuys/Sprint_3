import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.ArrayList;
import static io.restassured.RestAssured.*;

public class CommonTestMethods {

    private String baseURI = "https://qa-scooter.praktikum-services.ru/api/v1/courier";

    public ArrayList<String> registerNewCourierAndReturnLoginPassword(){

        String courierLogin = RandomStringUtils.randomAlphabetic(10);
        String courierPassword = RandomStringUtils.randomAlphabetic(10);
        String courierFirstName = RandomStringUtils.randomAlphabetic(10);

        ArrayList<String> loginPass = new ArrayList<>();

        String registerRequestBody = "{\"login\":\"" + courierLogin + "\","
                + "\"password\":\"" + courierPassword + "\","
                + "\"firstName\":\"" + courierFirstName + "\"}";

        Response response =  given()
                .header("Content-type", "application/json")
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
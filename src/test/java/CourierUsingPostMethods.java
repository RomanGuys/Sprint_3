import io.restassured.response.Response;
import java.util.ArrayList;
import static io.restassured.RestAssured.*;

public class CourierUsingPostMethods {

    private String baseURI = "https://qa-scooter.praktikum-services.ru/api/v1/courier";
    Response courierAddResponse;

    public void courierAdd(ArrayList<String> courierData){
        String courierLogin = courierData.get(0);
        String courierPassword = courierData.get(1);
        String courierFirstName = courierData.get(2);
        String registerRequestBody = "{\"login\":\"" + courierLogin + "\","
                + "\"password\":\"" + courierPassword + "\","
                + "\"firstName\":\"" + courierFirstName + "\"}";

        Response response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post(baseURI);
        courierAddResponse = response;
    }

}
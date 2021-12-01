import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;

public class CourierDeleteClient extends ApiClient {

    private String baseURI = "api/v1/courier/";

    @Step("Удаление курьера")
    public void deleteCourier(int id){
                given()
                .spec(getBaseSpec())
                .when()
                .delete(baseURI + id);
    }
}

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetOrderClient extends ApiClient {

    private String baseURI = "api/v1/orders";

    @Step("Получение заказов")
    public Response getOrders(int courierId) {
        return given()
                .spec(getBaseSpec())
                .and()
                .queryParam("courierId", courierId)
                .when()
                .get(baseURI);
    }


}

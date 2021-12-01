import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetOrderClient extends ApiClient {

    private String baseURI = "api/v1/orders";
    Response getOrderResponse;

    @DisplayName("Получение заказов")
    public void getOrders(int courierId) {
        Response response = given()
                .spec(getBaseSpec())
                .and()
                .queryParam("courierId", courierId)
                .when()
                .get(baseURI);
        getOrderResponse = response;
    }


}

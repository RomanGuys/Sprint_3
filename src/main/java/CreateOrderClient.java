import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CreateOrderClient extends ApiClient {

    String baseURI = "api/v1/orders";
    Response createOrderResponse;

    @DisplayName("Создание заказа")
    public void createOrder(Order order){
        Response response = given()

                .spec(getBaseSpec())
                .and()
                .body(order).log().all()
                .when()
                .post(baseURI);
        System.out.println(response.body().asString());
        createOrderResponse = response;
    }
}

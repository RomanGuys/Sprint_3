import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CreateOrderClient extends ApiClient {

    String baseURI = "api/v1/orders";

    @Step("Создание заказа")
    public Response createOrder(Order order){
        return given()
                .spec(getBaseSpec())
                .and()
                .body(order)
                .when()
                .post(baseURI);
    }
}

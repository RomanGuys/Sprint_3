import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class CancelOrderClient extends ApiClient {

    Response cancelOrderResponse;
    private String baseURI = "https://qa-scooter.praktikum-services.ru/api/v1/orders/cancel";

    public void cancelOrder(int id){
        Response response =  given()
                .spec(getBaseSpec())
                .and()
                .body(id)
                .when()
                .put(baseURI);

        cancelOrderResponse = response;
    }
}

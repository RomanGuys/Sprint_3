import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CreateOrderClient extends ApiClient {

    String baseURI = "api/v1/orders";
    Response createOrderResponse;

    public void createOrder(String orderBody){
        Response response = given()

                .spec(getBaseSpec())
                .and()
                .body(orderBody)
                .when()
                .post(baseURI);

        createOrderResponse = response;
    }

    String defaultOrderData = "{\n" +
            "    \"firstName\": \"Naruto\",\n" +
            "    \"lastName\": \"Uchiha\",\n" +
            "    \"address\": \"Konoha, 142 apt.\",\n" +
            "    \"metroStation\": 4,\n" +
            "    \"phone\": \"+7 800 355 35 35\",\n" +
            "    \"rentTime\": 5,\n" +
            "    \"deliveryDate\": \"2020-06-06\",\n" +
            "    \"comment\": \"Saske, come back to Konoha\"\n" +
            "}";

}

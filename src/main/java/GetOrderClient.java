import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetOrderClient {

    private String baseURI = "https://qa-scooter.praktikum-services.ru/api/v1/orders";
    Response getOrderResponse;

    public void getOrders(int courierId) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .queryParam("courierId", courierId)
                .when()
                .get(baseURI);
        getOrderResponse = response;
    }


}

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class AcceptOrderTestMethods {


    private String baseURI = "https://qa-scooter.praktikum-services.ru/api/v1/orders/accept/";

    public void acceptOrder(int orderId, int courierId){
            Response response =  given()
                    .header("Content-type", "application/json")
                    .and()
                    .queryParam("courierId",courierId)
                    .when()
                    .put(baseURI + orderId);

    }
}

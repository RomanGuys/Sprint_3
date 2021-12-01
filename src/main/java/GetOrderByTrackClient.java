import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetOrderByTrackClient {

    private String baseURI = "https://qa-scooter.praktikum-services.ru/api/v1/orders/track";

    public int getOrderIdByTrack(int trackId){
        Response response =  given()
                .header("Content-type", "application/json")
                .and()
                .queryParam("t",trackId)
                .when()
                .get(baseURI);
        return response.body().path("order.id");
    }

}

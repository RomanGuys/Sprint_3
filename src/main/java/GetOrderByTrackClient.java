import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetOrderByTrackClient extends ApiClient {

    private String baseURI = "api/v1/orders/track";

    @Step("Получение айди заказа")
    public int getOrderIdByTrack(int trackId){
        Response response =  given()
                .spec(getBaseSpec())
                .and()
                .queryParam("t",trackId)
                .when()
                .get(baseURI);
        return response.body().path("order.id");
    }

}

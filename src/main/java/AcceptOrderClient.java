import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;

public  class AcceptOrderClient extends ApiClient{
    

        private String baseURI = "api/v1/orders/accept/";

        @Step("Подтверждение заказа")
        public void acceptOrder(int orderId, int courierId){
                        given()
                        .spec(getBaseSpec())
                        .and()
                        .queryParam("courierId",courierId)
                        .when()
                        .put(baseURI + orderId);
    
        }
    }
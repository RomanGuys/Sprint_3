import static io.restassured.RestAssured.given;

public  class AcceptOrderClient extends ApiClient{
    

        private String baseURI = "api/v1/orders/accept/";

        public void acceptOrder(int orderId, int courierId){
                        given()
                        .spec(getBaseSpec())
                        .and()
                        .queryParam("courierId",courierId)
                        .when()
                        .put(baseURI + orderId);
    
        }
    }
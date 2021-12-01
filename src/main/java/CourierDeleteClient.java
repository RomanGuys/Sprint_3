import static io.restassured.RestAssured.given;

public class CourierDeleteClient extends ApiClient {

    private String baseURI = "api/v1/courier/";

    public void deleteCourier(int id){
                given()
                .spec(getBaseSpec())
                .when()
                .delete(baseURI + id);
    }
}

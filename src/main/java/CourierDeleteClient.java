import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierDeleteClient extends ApiClient {

    private String baseURI = "api/v1/courier/";

    public void deleteCourier(int id){
        System.out.println(id);
        Response response = given()
                .spec(getBaseSpec())
                .when()
                .delete(baseURI + id);
    }
}

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierDeleteClient {

    private String baseURI = "https://qa-scooter.praktikum-services.ru/api/v1/courier/";

    public void deleteCourier(int id){
        System.out.println(id);
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete(baseURI + id);
    }
}

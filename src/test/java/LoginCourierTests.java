import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class LoginCourierTests {
    CourierDeleteClient courierDeleteClient = new CourierDeleteClient();
    CourierLoginClient courierLoginClient = new CourierLoginClient();
    CourierCreateClient courierCreateClient = new CourierCreateClient();


    @Test
    @DisplayName("Позитивный тест логина курьера")
    public void courierLoginPositiveTest(){
        Courier courier = Courier.getRandom();
        courierCreateClient.courierAdd(courier);
        courierLoginClient.courierLogin(CourierCredentials.from(courier));
        courierLoginClient.courierLoginResponse.then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("id", notNullValue());
        int courierId = courierLoginClient.courierLoginResponse.path("id");
        courierDeleteClient.deleteCourier(courierId);

    }

    @Test
    @DisplayName("Негативный тест без обязательного поля")
    public void courierLoginNegativeWOLogin(){
//        ArrayList<String> data = courierCreateClient.registerNewCourierAndReturnLoginPassword();
//        ArrayList<String> loginData = new ArrayList<>(Arrays.asList("", data.get(1)));
        CourierCredentials courierCredentials = new CourierCredentials(null, RandomStringUtils.randomAlphabetic(10));
        courierLoginClient.courierLogin(courierCredentials);
        courierLoginClient.courierLoginResponse.then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Логин и пароль не матчатся")
        public void courierNegativeLogPassNotMatchTest() {
//        ArrayList<String> data = courierCreateClient.registerNewCourierAndReturnLoginPassword();
//        ArrayList<String> loginData = new ArrayList<>(Arrays.asList(data.get(0), "notyetapass"));
        Courier courier = Courier.getRandom();
        courierCreateClient.courierAdd(courier);
        courier.login = RandomStringUtils.randomAlphabetic(10);
        courierLoginClient.courierLogin(CourierCredentials.from(courier));
        courierLoginClient.courierLoginResponse.then()
                .assertThat()
                .statusCode(404)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Не существующий курьер")
        public void courierLoginUnexistNegativeTest(){
//        String login = RandomStringUtils.randomAlphabetic(10);
//        String pass = RandomStringUtils.randomAlphabetic(10);
//        ArrayList<String> loginData = new ArrayList<>(Arrays.asList(login,pass));
        Courier courier = Courier.getRandom();
        courierLoginClient.courierLogin(CourierCredentials.from(courier));
        courierLoginClient.courierLoginResponse.then()
                .assertThat()
                .statusCode(404)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }
}

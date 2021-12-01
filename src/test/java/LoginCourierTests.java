import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.hamcrest.Matchers.*;

public class LoginCourierTests {
    CourierDeleteClient courierDeleteClient = new CourierDeleteClient();
    CourierLoginClient courierLoginClient = new CourierLoginClient();
    CourierCreateClient courierCreateClient = new CourierCreateClient();


    @Test
    @DisplayName("Позитивный тест логина курьера")
    public void courierLoginPositiveTest(){
        courierLoginClient.courierLogin(courierCreateClient.registerNewCourierAndReturnLoginPassword());
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
        ArrayList<String> data = courierCreateClient.registerNewCourierAndReturnLoginPassword();
        ArrayList<String> loginData = new ArrayList<>(Arrays.asList("", data.get(1)));
        courierLoginClient.courierLogin(loginData);
        courierLoginClient.courierLoginResponse.then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Логин и пароль не матчатся")
        public void courierNegativeLogPassNotMatchTest() {
        ArrayList<String> data = courierCreateClient.registerNewCourierAndReturnLoginPassword();
        ArrayList<String> loginData = new ArrayList<>(Arrays.asList(data.get(0), "notyetapass"));
        courierLoginClient.courierLogin(loginData);
        courierLoginClient.courierLoginResponse.then()
                .assertThat()
                .statusCode(404)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Не существующий курьер")
        public void courierLoginUnexistNegativeTest(){
        String login = RandomStringUtils.randomAlphabetic(10);
        String pass = RandomStringUtils.randomAlphabetic(10);
        ArrayList<String> loginData = new ArrayList<>(Arrays.asList(login,pass));
        courierLoginClient.courierLogin(loginData);
        courierLoginClient.courierLoginResponse.then()
                .assertThat()
                .statusCode(404)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }
}

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

@DisplayName("Тесты на создание курьера")
public class CreateCourierTests {
    CourierLoginClient courierLoginClient;
    CourierCreateClient courierUsingPostMethods;
    CourierDeleteClient courierDeleteClient;

    @Before
    public void setUp(){
        courierLoginClient = new CourierLoginClient();
        courierUsingPostMethods = new CourierCreateClient();
        courierDeleteClient = new CourierDeleteClient();
    }

    @Test
    @DisplayName("Позивитные проверки создания курьера")
    public void createCourierPositiveTest(){
        Courier courier = Courier.getRandom();
        courierUsingPostMethods.courierAdd(courier)
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .body("ok", equalTo(true));
        courierDeleteClient.deleteCourier(courierLoginClient.getCourierId(CourierCredentials.from(courier)));
    }

    @Test
    @DisplayName("Негативка с одинаковым логином")
    public void createCourierDuplicateLoginTest(){
        Courier courier = Courier.getRandom();
        courierUsingPostMethods.courierAdd(courier);
        courierUsingPostMethods.courierAdd(courier).
                then()
                .assertThat()
                .statusCode(409)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
        courierDeleteClient.deleteCourier(courierLoginClient.getCourierId(CourierCredentials.from(courier)));

    }

    @Test
    @DisplayName("Негативка создания курьера без обязательного поля(Логин)")
    public void createCourierWORequiredFieldLoginTest(){
        Courier courier = Courier.getRandomWOLogin();
        courierUsingPostMethods.courierAdd(courier)
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Негативка создания курьера без обязательного поля(Пароль)")
    public void createCourierWORequiredFieldPasswordTest(){
        Courier courier = Courier.getRandomWOPass();
        courierUsingPostMethods.courierAdd(courier)
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Негативка создания курьера без обязательного поля(Имя)")
    public void createCourierWORequiredFieldNameTest(){
        Courier courier = Courier.getRandomWOName();
        courierUsingPostMethods.courierAdd(courier)
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

}

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;


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
        courierUsingPostMethods.courierAdd(courier);
        courierUsingPostMethods.courierAddResponse.then()
                .assertThat()
                .statusCode(201)
                .and()
                .body("ok", equalTo(true));
        courierDeleteClient.deleteCourier(courierLoginClient.getCourierId(courier));
    }

    @Test
    @DisplayName("Негативка с одинаковым логином")
    public void createCourierDuplicateLoginTest(){
        Courier courier = Courier.getRandom();
        courierUsingPostMethods.courierAdd(courier);
        courierUsingPostMethods.courierAdd(courier);
        courierUsingPostMethods.courierAddResponse.then()
                .assertThat()
                .statusCode(409)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

    }

    @Test
    @DisplayName("Негативка создания курьера без обязательного поля(Логин)")
    public void createCourierWORequiredFieldLoginTest(){
        Courier courier = Courier.getRandomWOLogin();
//        ArrayList<String> data = new ArrayList<>(Arrays.asList("", "glop", "bandit"));
        courierUsingPostMethods.courierAdd(courier);
        courierUsingPostMethods.courierAddResponse.then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Негативка создания курьера без обязательного поля(Пароль)")
    public void createCourierWORequiredFieldPasswordTest(){
        Courier courier = Courier.getRandomWOPass();
//        String randomLogin = RandomStringUtils.randomAlphabetic(10);
//        ArrayList<String> data = new ArrayList<>(Arrays.asList(randomLogin, "", "bandit"));
        courierUsingPostMethods.courierAdd(courier);
        courierUsingPostMethods.courierAddResponse.then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Негативка создания курьера без обязательного поля(Имя)")
    public void createCourierWORequiredFieldNameTest(){
//        String randomLogin = RandomStringUtils.randomAlphabetic(10);
//        String randomPass = RandomStringUtils.randomAlphabetic(10);
//        ArrayList<String> data = new ArrayList<>(Arrays.asList(randomLogin, randomPass, ""));
        Courier courier = Courier.getRandomWOName();
        courierUsingPostMethods.courierAdd(courier);
        courierUsingPostMethods.courierAddResponse.then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

}

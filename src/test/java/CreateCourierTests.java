import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;


public class CreateCourierTests {
    CommonTestMethods commonTestMethods = new CommonTestMethods();
    CourierUsingPostMethods courierUsingPostMethods = new CourierUsingPostMethods();
    CourierDeleteTestMethods courierDeleteTestMethods = new CourierDeleteTestMethods();

    @Test
    @DisplayName("Позивитные проверки создания курьера")
    public void createCourierPositiveTest(){
        ArrayList<String> data = new ArrayList(Arrays.asList("testatest","glop","gaf"));
        courierUsingPostMethods.courierAdd(data);
        courierUsingPostMethods.courierAddResponse.then()
                .assertThat()
                .statusCode(201)
                .and()
                .body("ok", equalTo(true));
        courierDeleteTestMethods.deleteCourier(commonTestMethods.getCourierId(data));
    }

    @Test
    @DisplayName("Негативка с одинаковым логином")
    public void createCourierDuplicateLoginTest(){
        ArrayList<String> data = commonTestMethods.registerNewCourierAndReturnLoginPassword();
        data.add("elpasa");
        courierUsingPostMethods.courierAdd(data);
        courierUsingPostMethods.courierAddResponse.then()
                .assertThat()
                .statusCode(409)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

    }

    @Test
    @DisplayName("Негативка создания курьера без обязательного поля")
    public void createCourierWORequiredFieldLoginTest(){
        ArrayList<String> data = new ArrayList<>(Arrays.asList("", "glop", "bandit"));
        courierUsingPostMethods.courierAdd(data);
        courierUsingPostMethods.courierAddResponse.then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Негативка создания курьера без обязательного поля")
    public void createCourierWORequiredFieldPasswordTest(){
        String randomLogin = RandomStringUtils.randomAlphabetic(10);
        ArrayList<String> data = new ArrayList<>(Arrays.asList(randomLogin, "", "bandit"));
        courierUsingPostMethods.courierAdd(data);
        courierUsingPostMethods.courierAddResponse.then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

}

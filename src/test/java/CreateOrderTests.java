import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTests {


    private final String orderData;

    CreateOrderTestMethods createOrderTestMethods = new CreateOrderTestMethods();
    CancelOrderTestMethods cancelOrderTestMethods = new CancelOrderTestMethods();

    public CreateOrderTests(String orderData) {
        this.orderData = orderData;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                {orderBodyTwoColors},
                {orderBodyGrayColor},
                {orderBodyBlackColor},
                {orderBodyWOColors},
        };
    }

    @Test
    @DisplayName("Позитивные тесты создания заказа")
        public void createOrderPositiveTest () {
            createOrderTestMethods.createOrder(orderData);
            createOrderTestMethods.createOrderResponse.then()
                    .assertThat()
                    .statusCode(201)
                    .and()
                    .body("track", notNullValue());

            //Закомменчено, так как метод отмены не работает (отменяем созданные заказы)
//            cancelOrderTestMethods.cancelOrder(createOrderTestMethods.createOrderResponse.path("track"));
//            cancelOrderTestMethods.cancelOrderResponse.then()
//                    .assertThat()
//                    .statusCode(200);
        }


    private static String orderBodyTwoColors = "{\n" +
            "    \"firstName\": \"Naruto\",\n" +
            "    \"lastName\": \"Uchiha\",\n" +
            "    \"address\": \"Konoha, 142 apt.\",\n" +
            "    \"metroStation\": 4,\n" +
            "    \"phone\": \"+7 800 355 35 35\",\n" +
            "    \"rentTime\": 5,\n" +
            "    \"deliveryDate\": \"2020-06-06\",\n" +
            "    \"comment\": \"Saske, come back to Konoha\",\n" +
            "    \"color\": [\n" +
            "        \"BLACK\", \"GRAY\"\n" +
            "    ]\n" +
            "}";

    private static String orderBodyBlackColor = "{\n" +
            "    \"firstName\": \"Naruto\",\n" +
            "    \"lastName\": \"Uchiha\",\n" +
            "    \"address\": \"Konoha, 142 apt.\",\n" +
            "    \"metroStation\": 4,\n" +
            "    \"phone\": \"+7 800 355 35 35\",\n" +
            "    \"rentTime\": 5,\n" +
            "    \"deliveryDate\": \"2020-06-06\",\n" +
            "    \"comment\": \"Saske, come back to Konoha\",\n" +
            "    \"color\": [\n" +
            "        \"BLACK\"\n" +
            "    ]\n" +
            "}";

    private static String orderBodyGrayColor = "{\n" +
            "    \"firstName\": \"Naruto\",\n" +
            "    \"lastName\": \"Uchiha\",\n" +
            "    \"address\": \"Konoha, 142 apt.\",\n" +
            "    \"metroStation\": 4,\n" +
            "    \"phone\": \"+7 800 355 35 35\",\n" +
            "    \"rentTime\": 5,\n" +
            "    \"deliveryDate\": \"2020-06-06\",\n" +
            "    \"comment\": \"Saske, come back to Konoha\",\n" +
            "    \"color\": [\n" +
            "        \"GRAY\"\n" +
            "    ]\n" +
            "}";

    private static String orderBodyWOColors = "{\n" +
            "    \"firstName\": \"Naruto\",\n" +
            "    \"lastName\": \"Uchiha\",\n" +
            "    \"address\": \"Konoha, 142 apt.\",\n" +
            "    \"metroStation\": 4,\n" +
            "    \"phone\": \"+7 800 355 35 35\",\n" +
            "    \"rentTime\": 5,\n" +
            "    \"deliveryDate\": \"2020-06-06\",\n" +
            "    \"comment\": \"Saske, come back to Konoha\"\n" +
            "}";


}



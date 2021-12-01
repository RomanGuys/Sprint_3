import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTests {


    private final String color;

    CreateOrderClient createOrderTestMethods;
    CancelOrderClient cancelOrderTestMethods;

    public CreateOrderTests(String color) {
        this.color = color;
    }

    @Before
    public void setUp() {
        createOrderTestMethods = new CreateOrderClient();
        cancelOrderTestMethods = new CancelOrderClient();
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"BLACK"},
                {"GRAY"},
                {"ALL"},
                {"NONE"},
        };
    }

    @Test
    @DisplayName("Позитивные тесты создания заказа")
    public void createOrderPositiveTest() {
        Order order = Order.getRandomOrder(color);
        createOrderTestMethods.createOrder(order);
        createOrderTestMethods.createOrderResponse.then()
                .assertThat()
                .statusCode(201)
                .and()
                .body("track", notNullValue());
//        Закомменчено, так как метод отмены не работает (отменяем созданные заказы)
//            cancelOrderClient.cancelOrder(createOrderClient.createOrderResponse.path("track"));
//            cancelOrderClient.cancelOrderResponse.then()
//                    .assertThat()
//                    .statusCode(200);
    }
}

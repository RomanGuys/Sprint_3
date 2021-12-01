import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.notNullValue;

@DisplayName("Тесты на создание заказа")
@RunWith(Parameterized.class)
public class CreateOrderTests {


    private final String color;

    CreateOrderClient createOrderClient;
    CancelOrderClient cancelOrderClient;

    public CreateOrderTests(String color) {
        this.color = color;
    }

    @Before
    public void setUp() {
        createOrderClient = new CreateOrderClient();
        cancelOrderClient = new CancelOrderClient();
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
        Response response = createOrderClient.createOrder(order);
        response.then()
                .assertThat()
                .statusCode(201)
                .and()
                .body("track", notNullValue());
//        Закомменчено, так как метод отмены не работает (отменяем созданные заказы)
//            cancelOrderClient.cancelOrder(response.path("track"))
//                    .then()
//                    .assertThat()
//                    .statusCode(200);
    }
}

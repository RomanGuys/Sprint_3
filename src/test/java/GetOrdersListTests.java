import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;

@DisplayName("Тест на получение списка заказов")
public class GetOrdersListTests {

    AcceptOrderClient acceptOrderTestMethods;
    CourierLoginClient courierLoginClient;
    CreateOrderClient createOrderClient;
    GetOrderClient getOrderClient;
    GetOrderByTrackClient getOrderByTrackClient;
    CourierCreateClient courierCreateClient;
    CancelOrderClient cancelOrderClient;

    @Before
    public void setUp(){
        acceptOrderTestMethods = new AcceptOrderClient();
        courierLoginClient = new CourierLoginClient();
        createOrderClient = new CreateOrderClient();
        getOrderClient = new GetOrderClient();
        getOrderByTrackClient = new GetOrderByTrackClient();
        courierCreateClient = new CourierCreateClient();
        cancelOrderClient = new CancelOrderClient();
    }

    @Test
    @DisplayName("Позитивный тест получения списка заказов")
    public void positiveGetOrdersListTest() {
        Courier courier = Courier.getRandom();
        Order order = Order.getRandomOrder("ALL");
        Response response = createOrderClient.createOrder(order);
        int orderId = getOrderByTrackClient.getOrderIdByTrack(response.body().path("track"));
        courierCreateClient.courierAdd(courier);
        int courierId = courierLoginClient.getCourierId(CourierCredentials.from(courier));
        acceptOrderTestMethods.acceptOrder(orderId, courierId);
        getOrderClient.getOrders(courierId)
                .then()
                .assertThat()
                .statusCode(200)
                .body("orders", notNullValue());

//        Закомменчено, так как метод отмены не работает (отменяем созданные заказы)
//            cancelOrderClient.cancelOrder(response.path("track"))
//                    .then()
//                    .assertThat()
//                    .statusCode(200);
    }

}


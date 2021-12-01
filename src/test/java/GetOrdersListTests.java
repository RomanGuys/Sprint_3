import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrdersListTests {

    AcceptOrderClient acceptOrderTestMethods = new AcceptOrderClient();
    CourierLoginClient courierLoginClient = new CourierLoginClient();
    CreateOrderClient createOrderClient = new CreateOrderClient();
    GetOrderClient getOrderClient = new GetOrderClient();
    GetOrderByTrackClient getOrderByTrackClient = new GetOrderByTrackClient();
    CourierCreateClient courierCreateClient = new CourierCreateClient();

    @Test
    @DisplayName("Позитивный тест получения списка заказов")
    public void positiveGetOrdersListTest(){
        createOrderClient.createOrder(createOrderClient.defaultOrderData);
        int orderId = getOrderByTrackClient.getOrderIdByTrack(createOrderClient.createOrderResponse.body().path("track"));
        int courierId = courierLoginClient.getCourierId(courierCreateClient.registerNewCourierAndReturnLoginPassword());
        acceptOrderTestMethods.acceptOrder(orderId, courierId);
        getOrderClient.getOrders(courierId);
        getOrderClient.getOrderResponse.then()
                .assertThat()
                .body("orders", notNullValue());
    }
}

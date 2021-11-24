import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrdersListTests {

    AcceptOrderTestMethods acceptOrderTestMethods = new AcceptOrderTestMethods();
    CommonTestMethods commonTestMethods = new CommonTestMethods();
    CreateOrderTestMethods createOrderTestMethods = new CreateOrderTestMethods();
    GetOrderTestMethods getOrderTestMethods = new GetOrderTestMethods();
    GetOrderByTrackTestMethods getOrderByTrackTestMethods = new GetOrderByTrackTestMethods();

    @Test
    public void positiveGetOrdersListTest(){
        createOrderTestMethods.createOrder(createOrderTestMethods.defaultOrderData);
        int orderId = getOrderByTrackTestMethods.getOrderIdByTrack(createOrderTestMethods.createOrderResponse.body().path("track"));
        int courierId = commonTestMethods.getCourierId(commonTestMethods.registerNewCourierAndReturnLoginPassword());
        acceptOrderTestMethods.acceptOrder(orderId, courierId);
        getOrderTestMethods.getOrders(courierId);
        getOrderTestMethods.getOrderResponse.then()
                .assertThat()
                .body("orders", notNullValue());
    }
}

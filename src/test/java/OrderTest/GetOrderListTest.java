package OrderTest;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import praktikum.order.CheckOrder;
import praktikum.order.OrderClient;


public class GetOrderListTest {

    @Test //получение списка заказов по станции метро
    @DisplayName("Confirmation of successful receipt of the order list")
    public void getOrdersListTest() {
        OrderClient order = new OrderClient();
        CheckOrder checkOrder = new CheckOrder();
        Response response = order.getOrdersList("233"); // получение списка по ближайшему метро
        checkOrder.getOkForGetAnOrderList(response); // проверка тела и статуса кода ответа
    }
}

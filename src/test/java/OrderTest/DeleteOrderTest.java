package OrderTest;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import praktikum.order.CheckOrder;
import praktikum.order.OrderClient;


public class DeleteOrderTest {

    @Test // тест успешной отмены заказа
    @DisplayName("Verification of the successful delete order")
    public void deleteOrderTest() {
        OrderClient order = new OrderClient();
        CheckOrder checkOrder = new CheckOrder();
        int track = order.getTrack("Игорь", "Гришин", "Алябьева, 4", "233", "832456987973", 20, "2020-09-21", "алё", new String[]{"BLACK"});
        Response response = order.cancelOrder(track);
        checkOrder.getOkForCancelOrder(response);
        //БАГ заказ не удаляется из БД, код ошибки 400, "Недостаточно данных для поиска"
    }
}

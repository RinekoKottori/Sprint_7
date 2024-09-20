import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import praktikum.CreateOrder;
import praktikum.CancelOrder;
import io.qameta.allure.junit4.*;


public class DeleteOrderTest {
    private int track;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test // тест успешной отмены заказа
    @DisplayName("Verification of the successful delete order")
    public void deleteOrderTest() {
        CreateOrder getTrack = new CreateOrder();
        int track = getTrack.getTrack("Игорь", "Гришин", "Алябьева, 4", "233", "832456987973", 20, "2020-09-21", "алё", new String[]{"BLACK"});
        CancelOrder orderTrack = new CancelOrder();
        Response response = orderTrack.cancelOrder(track);
        orderTrack.getOkForCancelOrder(response);
        //БАГ заказ не удаляется из БД, код ошибки 400, "Недостаточно данных для поиска"
    }
}

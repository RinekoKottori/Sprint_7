package OrderTest;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.order.CheckOrder;
import praktikum.order.OrderClient;


@RunWith(Parameterized.class)
public class CreateOrderTest {

    int track;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;


    public CreateOrderTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @After //отмена заказа
    public void cancelOrder() {
        OrderClient orderTrack = new OrderClient();
        orderTrack.cancelOrder(track);
    }

    @Parameterized.Parameters
    public static Object[][] createOrder() {
        return new Object[][]{
                {"Игорь", "Гришин", "Алябьева, 4", "233", "832456987973", 20, "2020-09-21", "алё", new String[]{"BLACK"}},
                {"Игорь", "Гришин", "Алябьева, 4", "233", "832456987973", 20, "2020-09-21", "алё", new String[]{"BLACK", "GREY"}},
                {"Игорь", "Гришин", "Алябьева, 4", "233", "832456987973", 20, "2020-09-21", "алё", new String[]{}},
                {"Игорь", "Гришин", "Алябьева, 4", "233", "832456987973", 20, "2020-09-21", "алё", new String[]{"GREY"}}
        };
    }

    @Test // тест успешного создания заказа
    @DisplayName("Verification of the successful creation of new orders with various color options")
    public void createOrderTest() {
        OrderClient createOrder = new OrderClient();
        CheckOrder checkOrder = new CheckOrder();
        Response response = createOrder.createOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color); //создание заказа
        track = response.then().extract().path("track"); // забор трек-номера зз для его последующей отмены
        checkOrder.getOkForCreateOrder(response); // проверка тела и статус кода ответа

    }
}

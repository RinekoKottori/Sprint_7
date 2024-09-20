import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.CreateOrder;
import praktikum.CancelOrder;
import io.qameta.allure.junit4.*;


@RunWith(Parameterized.class)
public class CreateOrderTest {

    private int track;
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

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @After //отмена заказа
    public void cancelOrder() {
        CancelOrder orderTrack = new CancelOrder();
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
        CreateOrder createOrder = new CreateOrder();
        Response response = createOrder.createOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color); //создание заказа
        createOrder.getOkForCreateOrder(response); // проверка тела и статус кода ответа
        track = response.then().extract().path("track"); // забор трек-номера зз для его последующей отмены
    }
}

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.*;
import praktikum.GetOrdersList;


public class GetOrderListTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test //получение списка заказов по станции метро
    @DisplayName("Confirmation of successful receipt of the order list")
    public void getOrdersListTest() {
        GetOrdersList list = new GetOrdersList();
        Response response = list.getOrdersList("233"); // получение списка по ближайшему метро
        list.getOkForGetAnOrderList(response); // проверка тела и статуса кода ответа
    }

}

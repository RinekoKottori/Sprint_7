package CourierTest;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.courier.CheckCourier;
import praktikum.courier.CourierClient;


public class LoginCourierPositiveTest {
    CourierClient logInCourier = new CourierClient();
    CheckCourier checkLoginIn = new CheckCourier();
    int courierId;

    @Before
    public void setUp() {
        CourierClient courier = new CourierClient();
        courier.createNewCourier("olegBat", "56274", "Олег");  // создание курьера
    }

    @After //удаление курьера
    public void deleteCourier() {
        CourierClient delete = new CourierClient();
        if (courierId != 0) {
            delete.deleteCourier(courierId);
        } // удаление курьера
    }

    @Test // тест об успешном логине курьера
    @DisplayName("Verification of the successful of login courier with existing login and password")
    public void loginCourierTest() {

        Response response = logInCourier.loginCourierForPositiveTest("olegBat", "56274"); // логин курьера
        courierId = checkLoginIn.getOkForLoginCourier(response); // проверка тела и статус кода ответа
    }
}

package CourierTest;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import praktikum.courier.CheckCourier;
import praktikum.courier.CourierClient;


public class LoginCourierNegativeTest {
    CourierClient courier = new CourierClient();
    CheckCourier checkCourier = new CheckCourier();
    CourierClient login = new CourierClient();
    int courierId;

    @After
    public void deleteCourier() {
        CourierClient delete = new CourierClient();
        if (courierId != 0) {
            delete.deleteCourier(courierId);
        } // удаление курьера
    }

    @Test // тест провальной авторизации если не передавать поле login
    @DisplayName("Verification of the impossibility of login courier without password")
    public void loginCourierWithoutLoginTest() {
        courier.createNewCourier("olegBat", "56274", "Олег");     // создание курьера
        Response response = login.loginCourierForNegativeTestWithoutLogin("56274"); // попытка входа курьера
        checkCourier.getMessage400ForLoginCourier(response); // проверка тела и статус кода ответа

    }

    @Test //тест провальной авторизации курьера с несуществующим логином
    @DisplayName("Verification of the impossibility of login courier with non-existent login")
    public void logInCourierWithWrongLogin() {
        Response response = login.loginCourierForPositiveTest("paraplane", "56274"); // попытка входа курьера
        checkCourier.getMessageForLogin404Courier(response); // проверка тела и статус кода ответа
    }
}


package CourierTest;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import praktikum.courier.CheckCourier;
import praktikum.courier.CourierClient;


public class CreateCourierNegativeTest {
    CourierClient courier = new CourierClient();
    CheckCourier checkCourier = new CheckCourier();
    CourierClient login = new CourierClient();
    int courierId;

    @After
    public void deleteCourier() {
        CourierClient delete = new CourierClient();
        if (courierId != 0) {
            delete.deleteCourier(courierId);
        }// удаление курьера
    }

    @Test // тест о провальном создании двух одинаковых курьеров
    @DisplayName("Verification of the impossibility of creating two identical couriers")
    public void createTwoCopesOfOneCouriersTest() {
        courier.createNewCourier("olegBat", "56274", "Олег"); // создание курьера
        Response loginResponse = login.loginCourierForPositiveTest("olegBat", "56274");
        courierId = checkCourier.getOkForLoginCourier(loginResponse); //login for get id

        Response secondResponse = courier.createSecondCourierForNegativeTest("olegBat", "56274", "Олег"); // создание второго курьера
        checkCourier.getMessageForCreatingNewCourier(secondResponse); //проверка тела и статус кода ответа

        // БАГ ожидаемое тело ответа: "Этот логин уже используется", фактическое: "Этот логин уже используется. Попробуйте другой."
    }


    @Test // тест о провальном создании двух курьеров с одинаковым полем login
    @DisplayName("Verification of the impossibility of creating two couriers with identical login")
    public void createOneCouriersWithSameLoginTest() {
        courier.createNewCourier("olegBat", "56274", "Олег"); // создание курьера
        Response loginResponse = login.loginCourierForPositiveTest("olegBat", "56274");
        courierId = checkCourier.getOkForLoginCourier(loginResponse); //login for get id

        Response secondResponse = courier.createSecondCourierForNegativeTest("olegBat", "4589", "Пётр"); // создание второго курьера
        checkCourier.getMessageForCreatingNewCourier(secondResponse); //проверка тела и статус кода ответа
        // БАГ ожидаемое тело ответа: "Этот логин уже используется", фактическое: "Этот логин уже используется. Попробуйте другой."
    }

    @Test // тест о провальном создании курьера без поля password
    @DisplayName("Verification of the impossibility of creating courier without key password")
    public void courierWithOutFieldPassword() {
        Response response = courier.createCourierForNegativeTestWithoutPassword("olegBat", "Олег"); // создание курьера
        checkCourier.getMessageForCreatingInvalidCourier(response); //проверка тела и статус кода ответа
    }
}

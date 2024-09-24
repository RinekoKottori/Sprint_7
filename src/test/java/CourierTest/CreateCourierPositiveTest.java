package CourierTest;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import praktikum.courier.CheckCourier;
import praktikum.courier.CourierClient;


public class CreateCourierPositiveTest {
    CourierClient createCourier = new CourierClient();
    CheckCourier checkCourier = new CheckCourier();
    CourierClient login = new CourierClient();
    int courierId;

    @After
    public void deleteCourier() {
        CourierClient delete = new CourierClient();
        // Логин для созданного курьера
        Response loginResponse = login.loginCourierForPositiveTest("olegBat", "56274");
        courierId = checkCourier.getOkForLoginCourier(loginResponse);

        // удаление курьера
        if (courierId != 0) {
            delete.deleteCourier(courierId);
        }
    }

    @Test // тест об успешном создании нового курьера
    @DisplayName("Verification of the successful creation of a new courier")
    public void createNewCourierTest() {

        Response createResponse = createCourier.createNewCourier("olegBat", "56274", "Олег"); // создание курьера
        checkCourier.getOkForCreatingNewCourier(createResponse);  // проверка тела и статус кода ответа

    }
}

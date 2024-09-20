import io.qameta.allure.junit4.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.CreateCourier;
import praktikum.DeleteCourier;

public class CreateCourierPositiveTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @After
    public void deleteCourier() {
        DeleteCourier delete = new DeleteCourier();
        delete.deleteCourier("olegBat", "56274"); // удаление курьера
    }

    @Test // тест об успешном создании нового курьера
    @DisplayName("Verification of the successful creation of a new courier")
    public void createNewCourierTest() {
        CreateCourier createCourier = new CreateCourier();
        Response response = createCourier.createNewCourier("olegBat", "56274", "Олег"); // создание курьера
        createCourier.getOkForCreatingNewCourier(response);  // проверка тела и статус кода ответа
    }
}

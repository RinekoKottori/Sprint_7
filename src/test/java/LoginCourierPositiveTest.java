import io.qameta.allure.junit4.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.CreateCourier;
import praktikum.DeleteCourier;
import praktikum.LoginCourier;


public class LoginCourierPositiveTest {
    private int courierID;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        CreateCourier createCourier = new CreateCourier();
        createCourier.createNewCourier("olegBat", "56274", "Олег");  // создание курьера
    }

    @After //удаление курьера
    public void deleteCourier() {
        DeleteCourier deleteCourier = new DeleteCourier();
        deleteCourier.deleteCourier("olegBat", "56274");
    }

    @Test // тест об успешном логине курьера
    @DisplayName("Verification of the successful of login courier with existing login and password")
    public void loginCourierTest() {
        LoginCourier logInCourier = new LoginCourier();
        Response response = logInCourier.loginCourierForPositiveTest("olegBat", "56274"); // логин курьера
        logInCourier.getOkForLoginCourier(response); // проверка тела и статус кода ответа
    }
}

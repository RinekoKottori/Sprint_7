import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import praktikum.CreateCourier;
import praktikum.DeleteCourier;
import praktikum.LoginCourier;
import io.qameta.allure.junit4.*;

import static io.restassured.parsing.Parser.JSON;


public class LoginCourierNegativeTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        Parser defaultParser = JSON;
    }

    @Test // тест провальной авторизации если не передавать поле login
    @DisplayName("Verification of the impossibility of login courier without password")
    public void loginCourierWithoutLoginTest() {
        CreateCourier createCourier = new CreateCourier();
        createCourier.createNewCourier("olegBat", "56274", "Олег");     // создание курьера
        try {
            LoginCourier loginCourier = new LoginCourier();
            Response response = loginCourier.loginCourierForNegativeTestWithoutLogin("56274"); // попытка входа курьера
            loginCourier.getMessage400ForLoginCourier(response); // проверка тела и статус кода ответа
        } finally {
            DeleteCourier deleteCourier = new DeleteCourier();
            deleteCourier.deleteCourier("olegBat", "56274"); // удаление курьера
        }
    }

    @Test //тест провальной авторизации курьера с несуществующим логином
    @DisplayName("Verification of the impossibility of login courier with non-existent login")
    public void logInCourierWithWrongLogin() {
        LoginCourier loginCourier = new LoginCourier();
        Response response = loginCourier.loginCourierForPositiveTest("paraplane", "56274"); // попытка входа курьера
        loginCourier.getMessageForLogin404Courier(response); // проверка тела и статус кода ответа
    }
}


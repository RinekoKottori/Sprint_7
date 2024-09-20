import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import praktikum.CreateCourier;
import praktikum.DeleteCourier;
import io.qameta.allure.junit4.*;


public class CreateCourierNegativeTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test // тест о провальном создании двух одинаковых курьеров
    @DisplayName("Verification of the impossibility of creating two identical couriers")
    public void createTwoCopesOfOneCouriersTest() {
        DeleteCourier delete = new DeleteCourier();
        CreateCourier createCourier = new CreateCourier();

        try {
            createCourier.creatCourierForNegativeTest("olegBat", "56274", "Олег"); // создание курьера
            Response secondResponse = createCourier.creatSecondCourierForNegativeTest("olegBat", "56274", "Олег"); // создание второго курьера
            createCourier.getMessageForCreatingNewCourier(secondResponse); //проверка тела и статус кода ответа
        } finally {
            delete.deleteCourier("olegBat", "56274"); // удаление курьера
        }
    }


    @Test // тест о провальном создании двух курьеров с одинаковым полем login
    @DisplayName("Verification of the impossibility of creating two couriers with identical login")
    public void createOneCouriersWithSameLoginTest() {
        DeleteCourier delete = new DeleteCourier();
        CreateCourier createCourier = new CreateCourier();

        try {
            createCourier.creatCourierForNegativeTest("olegBat", "56274", "Олег"); // создание курьера
            Response secondResponse = createCourier.creatSecondCourierForNegativeTest("olegBat", "4589", "Пётр"); // создание второго курьера
            createCourier.getMessageForCreatingNewCourier(secondResponse); //проверка тела и статус кода ответа
        } finally {
            delete.deleteCourier("olegBat", "56274"); // удаление курьера
        }
    }

    @Test // тест о провальном создании курьера без поля password
    @DisplayName("Verification of the impossibility of creating courier without key password")
    public void createCourierWithOutFieldPassword() {
        CreateCourier createCourier = new CreateCourier();
        Response response = createCourier.createCourierForNegativeTestWithoutPassword("olegBat", "Олег"); // создание курьера
        createCourier.getMessageForCreatingInvalidCourier(response); //проверка тела и статус кода ответа
    }
}

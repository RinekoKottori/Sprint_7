package praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import praktikum.client.Client;

import static io.restassured.RestAssured.given;


public class CourierClient extends Client {


    // Методы создания курьера

    @Step("Send POST request to /api/v1/courier")
    public Response createNewCourier(String login, String password, String firstName) {
        Courier courier = new Courier(login, password, firstName);
        return getPostCourierCreate(courier);

    }

    @Step("Send POST request to /api/v1/courier")
    public Response createSecondCourierForNegativeTest(String login, String password, String firstName) {
        Courier courier = new Courier(login, password, firstName);
        return getPostCourierCreate(courier);

    }

    @Step("Send POST request to /api/v1/courier without password")
    public Response createCourierForNegativeTestWithoutPassword(String login, String firstName) {
        Courier courier = new Courier(login, firstName);
        return getPostCourierCreate(courier);

    }


    // Методы для удаления курьера

    @Step("Send DELETE request to /api/v1/courier/")
    public Response deleteCourier(int id) {
        return given()
                .spec(speca())
                .delete("/api/v1/courier/" + id);
    }


    // Методы для логирования курьера

    @Step("Send POST request to /api/v1/courier/login")
    public Response loginCourierForPositiveTest(String login, String password) {
        CourierCredentials logInCourier = new CourierCredentials(login, password);
        return getPostCourierLogin(logInCourier);
    }

    @Step("Send POST request to /api/v1/courier/login without  key login")
    public Response loginCourierForNegativeTestWithoutLogin(String password) {
        CourierCredentials logInCourier = new CourierCredentials(password);
        return getPostCourierLogin(logInCourier);
    }

}

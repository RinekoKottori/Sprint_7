package praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourier {
    int courierId;
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public LoginCourier(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginCourier(String password) {
        this.password = password;
    }

    public LoginCourier() {

    }

    @Step("Send POST request to /api/v1/courier/login and get key id")
    public void loginCourier(String login, String password) {
        LoginCourier logInCourier = new LoginCourier(login, password);
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(logInCourier)
                .when()
                .post("/api/v1/courier/login");
        courierId = response.then().extract().path("id");
    }

    @Step("Send POST request to /api/v1/courier/login")
    public Response loginCourierForPositiveTest(String login, String password) {
        LoginCourier logInCourier = new LoginCourier(login, password);
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(logInCourier)
                .when()
                .post("/api/v1/courier/login");
    }

    @Step("Verify that login courier is OK and status code 200")
    public void getOkForLoginCourier(Response response) {
        response.then()
                .assertThat()
                .body("id", notNullValue())  // Проверка тела ответа
                .and()
                .statusCode(200); // проверка статус кода ответа
    }

    @Step("Send POST request to /api/v1/courier/login without  key login")
    public Response loginCourierForNegativeTestWithoutLogin(String password) {
        LoginCourier loginCourier = new LoginCourier(password);
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(loginCourier)
                .when()
                .post("/api/v1/courier/login");
    }

    @Step("Verify that login courier is get error message and status code 400")
    public void getMessage400ForLoginCourier(Response response) {
        response.then()
                .assertThat()
                .statusCode(400);   // проверка статус кода ответа
        response.then()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа")); // Проверка тела ответа

    }

    @Step("Verify that login courier is get error message and status code 404")
    public void getMessageForLogin404Courier(Response response) {
        response.then()
                .assertThat()
                .statusCode(404);   // проверка статус кода ответа
        response.then()
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена")); // Проверка тела ответа

    }

}

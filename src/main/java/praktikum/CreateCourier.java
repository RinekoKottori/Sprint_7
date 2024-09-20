package praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class CreateCourier {
    private String login;
    private String password;
    private String firstName;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public CreateCourier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CreateCourier(String login, String firstName) {
        this.login = login;
        this.firstName = firstName;
    }

    public CreateCourier() {

    }


    @Step("Send POST request to /api/v1/courier")
    public Response createNewCourier(String login, String password, String firstName) {
        CreateCourier createCourier = new CreateCourier(login, password, firstName);
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(createCourier)
                .when()
                .post("/api/v1/courier");

    }

    @Step("Verify that courier is created and status code 201")
    public void getOkForCreatingNewCourier(Response response) {
        response.then()
                .assertThat()
                .body("ok", equalTo(true))  // Проверка тела ответа
                .and()
                .statusCode(201);
    }

    @Step("Send POST request to /api/v1/courier")
    public void creatCourierForNegativeTest(String login, String password, String firstName) {
        CreateCourier createCourier = new CreateCourier(login, password, firstName);
        given() // создание первого курьера
                .header("Content-type", "application/json")
                .and()
                .body(createCourier)
                .when()
                .post("/api/v1/courier");
    }

    @Step("Send POST request to /api/v1/courier")
    public Response creatSecondCourierForNegativeTest(String login, String password, String firstName) {
        CreateCourier createCourier = new CreateCourier(login, password, firstName);
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(createCourier)
                .when()
                .post("/api/v1/courier");

    }

    @Step("Verify that creation courier got - error message and status code 401")
    public void getMessageForCreatingNewCourier(Response secondResponse) {
        secondResponse.then()
                .assertThat()
                .body("message", equalTo("Этот логин уже используется"))  // Проверка тела ответа
                .and()
                .statusCode(409);
    }

    @Step("Send POST request to /api/v1/courier without password")
    public Response createCourierForNegativeTestWithoutPassword(String login, String firstName) {
        CreateCourier createCourier = new CreateCourier(login, firstName);
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(createCourier)
                .when()
                .post("/api/v1/courier");

    }

    @Step("Verify that creation courier got - error message and status code 400")
    public void getMessageForCreatingInvalidCourier(Response response) {
        response.then()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))  // Проверка тела ответа
                .and()
                .statusCode(400);
    }

}

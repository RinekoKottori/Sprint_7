package praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import praktikum.client.Client;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotEquals;

public class CheckCourier extends Client {

    // Проверки на создание курьера

    @Step("Verify that courier is created and status code 201")
    public void getOkForCreatingNewCourier(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_CREATED) // Проверка статуса курьера
                .and()
                .body("ok", equalTo(true));  // Проверка тела ответа
    } // не изменился

    @Step("Verify that creation courier got - error message and status code 409")
    public void getMessageForCreatingNewCourier(Response secondResponse) {
        secondResponse.then()
                .assertThat()
                .statusCode(HTTP_CONFLICT) // Проверка статуса кода ответа
                .and()
                .body("message", equalTo("Этот логин уже используется")); // Проверка тела ответа
    } // не изменился

    @Step("Verify that creation courier got - error message and status code 400")
    public void getMessageForCreatingInvalidCourier(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST) // проверка кода ответа
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи")); // Проверка тела ответа
    } // не изменился

//Проверки на логирование курьера

    @Step("Verify that login courier is OK and status code 200 and get id")
    public int getOkForLoginCourier(Response response) {
        int id = response
                .then()
                .assertThat()
                .statusCode(HTTP_OK) // проверка статус кода ответа
                .extract().body().path("id");    // Проверка тела ответа

       assertNotEquals(0, id);
        return id;
    }

    @Step("Verify that login courier is get error message and status code 400")
    public void getMessage400ForLoginCourier(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST);   // проверка статус кода ответа
        response.then()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа")); // Проверка тела ответа

    }

    @Step("Verify that login courier is get error message and status code 404")
    public void getMessageForLogin404Courier(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_NOT_FOUND);   // проверка статус кода ответа
        response.then()
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена")); // Проверка тела ответа

    }
}

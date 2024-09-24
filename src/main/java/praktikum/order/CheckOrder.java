package praktikum.order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import praktikum.client.Client;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.*;

public class CheckOrder extends Client {

    @Step("Verify that order is created and status code 201")
    public void getOkForCreateOrder(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_CREATED)  // проверка статус кода ответа
                .and()
                .body("track", notNullValue()); // Проверка тела ответа

    }

    @Step("Verify that get an order list is OK and status code 200")
    public void getOkForGetAnOrderList(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_OK) // проверка статус кода ответа
                .and()
                .body("orders", notNullValue()) // проверка, что тело ответа не пустое
                .and()
                .body("orders.find {it.track != null}", notNullValue()); // проверка, что в теле ответа есть ключ track
    }

    @Step("Verify that cancel order is OK and status code 200")
    public void getOkForCancelOrder(Response response) {
        response.then()
                .assertThat()
                .statusCode(HTTP_OK) // проверка статуса кода ответа
                .and()
                .body("$", hasKey("ok")) //проверка наличия в теле ответа ключа ok
                .and()
                .body("ok", equalTo(true)); // проверка содержимого тела ответа
    }

}

package praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersList {

    public GetOrdersList() {

    }

    @Step("Send GET request to api/v1/orders?limit=10&page=0&nearestStation=[\"__\"]")
    public Response getOrdersList(String metroStation) {
        String handle = String.format("api/v1/orders?limit=10&page=0&nearestStation=[\"%s\"]", metroStation);
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(handle);
    }

    @Step("Verify that get an order list is OK and status code 200")
    public void getOkForGetAnOrderList(Response response) {
        response.then().assertThat().body("orders", notNullValue()) // проверка, что тело ответа не пустое
                .statusCode(200); // проверка статус кода ответа
        response.then()
                .assertThat().body("orders.find {it.track != null}", notNullValue()); // проверка, что в теле ответа есть ключ track
    }

}

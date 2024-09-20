package praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;


public class CancelOrder {
    private int track;

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }


    public CancelOrder(int track) {
        this.track = track;
    }

    public CancelOrder() {

    }
    @Step("Send PUT request to /api/v1/orders/cancel")
    public Response cancelOrder(int track) {
        CancelOrder delete = new CancelOrder(track);
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(delete)
                .when()
                .put("/api/v1/orders/cancel");
    }

    @Step("Verify that cancel order is OK and status code 200")
    public void getOkForCancelOrder(Response response) {
        response.then().assertThat().body("$", hasKey("ok")) //проверка наличия в теле ответа ключа ok
                .statusCode(200); // проверка статуса кода ответа
        response.then().assertThat().body("ok", equalTo(true)); // проверка содержимого тела ответа
    }

}

package praktikum;

import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class DeleteCourier {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeleteCourier(String id) {
        this.id = id;
    }

    public DeleteCourier() {

    }

    @Step("Send DELETE request to /api/v1/courier/")
    public void deleteCourier(String login, String password) {
        LoginCourier logIn = new LoginCourier();
        logIn.loginCourier(login, password);
        given()
                .delete("/api/v1/courier/" + logIn.courierId);
    }
}

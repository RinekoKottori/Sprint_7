package praktikum.order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import praktikum.client.Client;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client {


    @Step("Send POST request to /api/v1/orders")
    public Response createOrder(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        return getPostOrders(order);
    }


    @Step("Send POST request to /api/v1/orders and get key track")
    public int getTrack(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        Response response = getPostOrders(order);

        return order.track = response.then().extract().path("track");
    }


    @Step("Send GET request to api/v1/orders?limit=10&page=0&nearestStation=[\"__\"]")
    public Response getOrdersList(String metroStation) {
        String handle = String.format("api/v1/orders?limit=10&page=0&nearestStation=[\"%s\"]", metroStation);
        return given()
                .spec(speca())
                .when()
                .get(handle);
    }


    @Step("Send PUT request to /api/v1/orders/cancel")
    public Response cancelOrder(int track) {
        Order cancel = new Order(track);
        return given()
                .spec(speca())
                .body(cancel)
                .when()
                .put(PATH_ORDER + "/cancel");
    }


}

package praktikum.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import praktikum.courier.Courier;
import praktikum.courier.CourierCredentials;
import praktikum.order.Order;

import static io.restassured.RestAssured.given;

public class Client {
    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";
    public static final String PATH_COURIER = "/api/v1/courier";
    public static final String PATH_ORDER = "/api/v1/orders";
    public static final String PATH_LOGIN_COURIER = "/api/v1/courier/login";



    public RequestSpecification speca() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URI)
                .build();
    }

    protected Response getPostCourierCreate(Courier courier) {
        return given()
                .spec(speca())
                .body(courier)
                .when()
                .post(PATH_COURIER);
    }

    protected Response getPostCourierLogin(CourierCredentials logInCourier) {
        return given()
                .spec(speca())
                .body(logInCourier)
                .when()
                .post(PATH_LOGIN_COURIER);
    }


    protected Response getPostOrders(Order order) {
        return given()
                .spec(speca())
                .body(order)
                .when()
                .post(PATH_ORDER);
    }

}
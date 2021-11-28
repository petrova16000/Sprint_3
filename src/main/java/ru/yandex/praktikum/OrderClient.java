package ru.yandex.praktikum;


import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient {

    private static final String ORDER_PATH = "/api/v1/orders";

    @Step("Создаем заказ")
    public int create(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .log().all()
                .when()
                .post(ORDER_PATH)
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("track");
    }

    @Step("Получаем список заказов")
    public Object getOrders() {
        return given()
                .spec(getBaseSpec())
                .log().all()
                .when()
                .get(ORDER_PATH)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("orders");
    }
}

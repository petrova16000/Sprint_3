package ru.yandex.praktikum;

import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;

public class CourierClient extends RestAssuredClient {

    private static final String COURIER_PATH = "api/v1/courier/";

    @Step("Создаем курьера")
    public boolean create(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .log().all()
                .when()
                .post(COURIER_PATH)
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("ok");
    }

    @Step("Создаем курьера")
    public String createCatchError(Courier courier, int code) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .log().all()
                .when()
                .post(COURIER_PATH)
                .then()
                .log().all()
                .assertThat()
                .statusCode(code)
                .extract()
                .path("message");
    }

    @Step("Логин курьера")
    public int login(CourierCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .log().all()
                .when()
                .post(COURIER_PATH + "login/")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
    }

    @Step("Логин курьера")
    public String loginCatchError(CourierCredentials credentials, int code) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .log().all()
                .when()
                .post(COURIER_PATH + "login/")
                .then()
                .log().all()
                .assertThat()
                .statusCode(code)
                .extract()
                .path("message");
    }

    @Step("Удаление курьера")
    public boolean delete(int courierId) {
        return given()
                .spec(getBaseSpec())
                .log().all()
                .when()
                .delete(COURIER_PATH + courierId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("ok");
    }
}
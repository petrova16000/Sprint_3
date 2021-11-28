package ru.yandex.praktikum.createTests;

import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.Courier;
import ru.yandex.praktikum.CourierClient;

import static org.junit.Assert.assertEquals;


public class CreateCourierWithErrorTest {

    private CourierClient courierClient;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @Test
    public void courierCantBeCreatedWithoutRequiredFieldPass() {
        Courier courier = Courier.getRandomWithoutPass();
        String errorMessage = courierClient.createCatchError(courier, 400);
        assertEquals("Недостаточно данных для создания учетной записи", errorMessage);
    }

    @Test
    public void courierCantBeCreatedWithoutRequiredFieldLogin() {
        Courier courier = Courier.getRandomWithoutLogin();
        String errorMessage = courierClient.createCatchError(courier, 400);
        assertEquals("Недостаточно данных для создания учетной записи", errorMessage);
    }

    @Test
    public void courierCantBeCreatedWithoutLoginPass() {
        String errorMessage = courierClient.createWithoutLogPass();
        assertEquals("Недостаточно данных для создания учетной записи", errorMessage);
    }
}

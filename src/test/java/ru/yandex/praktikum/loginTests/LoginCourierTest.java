package ru.yandex.praktikum.loginTests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.Courier;
import ru.yandex.praktikum.CourierClient;
import ru.yandex.praktikum.CourierCredentials;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


public class LoginCourierTest {

    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }


    @Test
    public void courierCanBeLogined() {
        Courier courier = Courier.getRandom();
        courierClient.create(courier);
        courierId = courierClient.login(new CourierCredentials(courier.login, courier.password));
        assertTrue("ID курьера 0", courierId != 0);
        assertNotEquals("ID курьера null", courierId, null);
    }

    @Test
    public void courierCantLoginWithoutPass() {
        Courier courier = Courier.getRandom();
        courierClient.create(courier);
        courierId = courierClient.login(new CourierCredentials(courier.login, courier.password));
        String message = courierClient.loginCatchError(new CourierCredentials(courier.login, ""), 400);
        assertEquals("Недостаточно данных для входа", message);
    }

    @Test
    public void courierCantLoginWithoutLogin() {
        Courier courier = Courier.getRandom();
        courierClient.create(courier);
        courierId = courierClient.login(new CourierCredentials(courier.login, courier.password));
        String message = courierClient.loginCatchError(new CourierCredentials("", courier.password), 400);
        assertEquals("Недостаточно данных для входа", message);
    }

    @Test
    public void courierCantLoginWithBadPass() {
        Courier courier = Courier.getRandom();
        courierClient.create(courier);
        courierId = courierClient.login(new CourierCredentials(courier.login, courier.password));
        courier.password = RandomStringUtils.randomAlphabetic(10);
        String message = courierClient.loginCatchError(new CourierCredentials(courier.login, courier.password), 404);
        assertEquals("Учетная запись не найдена", message);
    }


    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }
}

package ru.yandex.praktikum.loginTests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import ru.yandex.praktikum.CourierClient;
import ru.yandex.praktikum.CourierCredentials;

import static org.junit.Assert.assertEquals;



public class LoginNonexistentCourierTest {

    @Test
    public void courierCantBeLogined() {
        CourierClient courierClient = new CourierClient();
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        String message = courierClient.loginCatchError(new CourierCredentials(login, password), 404);
        assertEquals("Учетная запись не найдена", message);
    }
}

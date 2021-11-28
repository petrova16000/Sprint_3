package ru.yandex.praktikum.createTests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.Courier;
import ru.yandex.praktikum.CourierClient;
import ru.yandex.praktikum.CourierCredentials;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateCourierTest {

    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }
    
    
    @Test
    public void courierCanBeCreatedWithValidData() {
        Courier courier = Courier.getRandom();
        boolean isCourierCreated = courierClient.create(courier);
        courierId = courierClient.login(new CourierCredentials(courier.login, courier.password));
        assertTrue("Курьер не создан", isCourierCreated);
        assertThat("ID курьера 0", courierId, is(not(0)));
    }

    @Test
    public void twoCourierCantBeIdentical() {
        Courier courier = Courier.getRandom();
        boolean isCourierCreated = courierClient.create(courier);
        courierId = courierClient.login(new CourierCredentials(courier.login, courier.password));
        String errorMessage = courierClient.createCatchError(courier, 409);
        assertTrue("Курьер не создан", isCourierCreated);
        assertEquals("Этот логин уже используется. Попробуйте другой.", errorMessage);
    }

    @Test
    public void twoCourierCantHaveSameLogin() {
        Courier courier = Courier.getRandom();
        courierClient.create(courier);
        courierId = courierClient.login(new CourierCredentials(courier.login, courier.password));
        courier.password = RandomStringUtils.randomAlphabetic(10);
        String errorMessage = courierClient.createCatchError(courier, 409);
        assertEquals("Этот логин уже используется. Попробуйте другой.", errorMessage);
    }


    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }
}

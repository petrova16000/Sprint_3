package ru.yandex.praktikum;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class createOrderTest {

    String color1;
    String color2;

    public createOrderTest(String color1, String color2) {
        this.color1 = color1;
        this.color2 = color2;
    }

    @Parameterized.Parameters
    public static Object[][] getColor() {
        return new Object[][]{
                {"BLACK", "GREY"},
                {"BLACK", ""},
                {"GREY", ""},
                {"", ""}
        };
    }

    @Test
    public void makeOrder() {
        String[] color = new String[]{color1, color2};
        Order order = Order.getRandom(color);
        OrderClient orderClient = new OrderClient();
        int track = orderClient.create(order);
        assertTrue(track != 0);
        assertNotEquals(track, null);
    }
}

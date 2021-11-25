package ru.yandex.praktikum;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;


public class GetOrdersListTest {

    @Test
    public void getOrdersList() {
        String[] color = new String[]{""};
        Order order = Order.getRandom(color);
        OrderClient orderClient = new OrderClient();
        orderClient.create(order);
        Object orders = orderClient.getOrders();
        assertNotEquals(orders, null);
    }
}

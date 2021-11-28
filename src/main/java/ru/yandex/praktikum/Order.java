package ru.yandex.praktikum;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class Order {

    public final String firstName;
    public final String lastName;
    public final String address;
    public final String metroStation;
    public final String phone;
    public final int rentTime;
    public final String comment;
    public final String[] color;

    public Order(String name, String lastName, String address, String metroStation, String phone, int rentTime, String comment, String[] color) {
        firstName = name;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.comment = comment;
        this.color = color;
    }

    public static Order getRandom(String[] color) {
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        final String lastName = RandomStringUtils.randomAlphabetic(10);
        final String address = RandomStringUtils.randomAlphabetic(10);
        final String metroStation = RandomStringUtils.randomAlphabetic(10);
        final String phone = RandomStringUtils.randomAlphabetic(10);
        final int rentTime = RandomUtils.nextInt();
        final String comment = RandomStringUtils.randomAlphabetic(10);
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, comment, color);
    }
}

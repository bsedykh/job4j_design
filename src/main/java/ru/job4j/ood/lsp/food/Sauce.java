package ru.job4j.ood.lsp.food;

import java.time.LocalDate;

public class Sauce extends Food {
    private final String color;

    public Sauce(String name, LocalDate createDate, LocalDate expiryDate, int price, int discount, String color) {
        super(name, createDate, expiryDate, price, discount);
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

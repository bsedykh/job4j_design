package ru.job4j.ood.lsp.food;

import java.time.LocalDate;

public class Banana extends Food {
    private final int size;

    public Banana(String name, LocalDate createDate, LocalDate expiryDate, int price, int discount, int size) {
        super(name, createDate, expiryDate, price, discount);
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}

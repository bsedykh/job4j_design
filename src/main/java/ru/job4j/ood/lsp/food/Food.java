package ru.job4j.ood.lsp.food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Food {
    private final String name;
    private final LocalDate createDate;
    private final LocalDate expiryDate;
    private final int price;
    private int discount;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, int price, int discount) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getExpiryPercentage(LocalDate currentDate) {
        if (currentDate.isBefore(createDate)) {
            return 0;
        }
        if (currentDate.isAfter(expiryDate)) {
            return 100;
        }
        var totalDays = ChronoUnit.DAYS.between(createDate, expiryDate);
        var expiredDays = ChronoUnit.DAYS.between(createDate, currentDate);
        return (int) ((double) expiredDays / totalDays * 100);
    }
}

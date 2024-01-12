package ru.job4j.ood.lsp.food;

import java.time.LocalDate;

public class Shop extends AbstractStore {
    @Override
    protected boolean process(Food product) {
        var expiryPercentage = product.getExpiryPercentage(LocalDate.now());
        var result = expiryPercentage >= 25 && expiryPercentage < 100;
        if (result && expiryPercentage > 75) {
            product.setDiscount(20);
        }
        return result;
    }
}

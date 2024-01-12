package ru.job4j.ood.lsp.food;

import java.time.LocalDate;

public class Trash extends AbstractStore {
    @Override
    protected boolean process(Food product) {
        return product.getExpiryPercentage(LocalDate.now()) == 100;
    }
}

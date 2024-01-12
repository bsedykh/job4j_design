package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private final List<Food> products = new ArrayList<>();

    protected abstract boolean process(Food product);

    @Override
    public boolean put(Food product) {
        boolean result = process(product);
        if (result) {
            products.add(product);
        }
        return result;
    }

    @Override
    public List<Food> getProducts() {
        return List.copyOf(products);
    }
}

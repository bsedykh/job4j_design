package ru.job4j.ood.lsp.food;

import java.util.List;

public interface Store {
    boolean put(Food product);
    List<Food> getProducts();
}

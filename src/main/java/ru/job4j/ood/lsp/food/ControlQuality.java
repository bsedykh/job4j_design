package ru.job4j.ood.lsp.food;

import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void sort(Food product) {
        for (var store : stores) {
            if (store.put(product)) {
                break;
            }
        }
    }

    public void resort() {
        var products = stores.stream()
                .flatMap(store -> store.unload().stream())
                .toList();
        products.forEach(this::sort);
    }
}

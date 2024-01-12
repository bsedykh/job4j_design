package ru.job4j.ood.lsp.food;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {
    @Test
    public void whenExpiredProductThenWarehouseIsEmpty() {
        Store store = new Warehouse();
        var now = LocalDate.now();
        Food product = new Banana("Banana",
                now.minusDays(25),
                now.plusDays(75),
                100,
                0,
                1);
        store.put(product);
        assertThat(store.getProducts().isEmpty()).isTrue();
    }

    @Test
    public void whenFreshProductThenWarehouseContainsProduct() {
        Store store = new Warehouse();
        var now = LocalDate.now();
        Food product = new Banana("Banana",
                now.minusDays(10),
                now.plusDays(90),
                100,
                0,
                1);
        store.put(product);
        assertThat(store.getProducts()).containsExactly(product);
    }
}

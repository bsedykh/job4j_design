package ru.job4j.ood.lsp.food;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class TrashTest {
    @Test
    public void whenExpiredProductThenTrashContainsProduct() {
        Store store = new Trash();
        var now = LocalDate.now();
        Food product = new Banana("Banana",
                now.minusDays(10),
                now,
                100,
                0,
                1);
        store.put(product);
        assertThat(store.getProducts()).containsExactly(product);
    }

    @Test
    public void whenFreshProductThenTrashIsEmpty() {
        Store store = new Trash();
        var now = LocalDate.now();
        Food product = new Banana("Banana",
                now,
                now.plusDays(10),
                100,
                0,
                1);
        store.put(product);
        assertThat(store.getProducts().isEmpty()).isTrue();
    }
}

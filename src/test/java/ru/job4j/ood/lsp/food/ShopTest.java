package ru.job4j.ood.lsp.food;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class ShopTest {
    private final MockDateProvider dateProvider = new MockDateProvider();
    private final LocalDate now = dateProvider.now();

    @Test
    public void whenExpiredProductThenShopIsEmpty() {
        Store store = new Shop(dateProvider);
        Food product = new Banana("Banana",
                now.minusDays(10),
                now,
                100,
                0,
                1);
        store.put(product);
        assertThat(store.getProducts().isEmpty()).isTrue();
    }

    @Test
    public void whenFreshProductThenShopContainsProduct() {
        Store store = new Shop(dateProvider);
        Food product = new Banana("Banana",
                now.minusDays(25),
                now.plusDays(75),
                100,
                0,
                1);
        store.put(product);
        assertThat(store.getProducts()).containsExactly(product);
    }

    @Test
    public void whenExpiringProductThenDiscount20() {
        Store store = new Shop(dateProvider);
        Food product = new Banana("Banana",
                now.minusDays(76),
                now.plusDays(24),
                100,
                0,
                1);
        store.put(product);
        assertThat(store.getProducts()).containsExactly(product);
        assertThat(product.getDiscount()).isEqualTo(20);
    }
}

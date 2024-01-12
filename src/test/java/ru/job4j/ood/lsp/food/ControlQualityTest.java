package ru.job4j.ood.lsp.food;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {
    private final Store warehouse = new Warehouse();
    private final Store shop = new Shop();
    private final Store trash = new Trash();
    private final ControlQuality controlQuality = new ControlQuality(
            List.of(warehouse, shop, trash));
    private final LocalDate now = LocalDate.now();

    @Test
    public void whenProductInWarehouse() {
        Food product = new Banana("Banana",
                now.minusDays(10),
                now.plusDays(90),
                100,
                0,
                1);
        controlQuality.process(product);
        assertThat(warehouse.getProducts()).containsExactly(product);
        assertThat(shop.getProducts().isEmpty()).isTrue();
        assertThat(trash.getProducts().isEmpty()).isTrue();
    }

    @Test
    public void whenProductInShop() {
        Food product = new Banana("Banana",
                now.minusDays(50),
                now.plusDays(50),
                100,
                0,
                1);
        controlQuality.process(product);
        assertThat(warehouse.getProducts().isEmpty()).isTrue();
        assertThat(shop.getProducts()).containsExactly(product);
        assertThat(trash.getProducts().isEmpty()).isTrue();
    }

    @Test
    public void whenProductInTrash() {
        Food product = new Banana("Banana",
                now.minusDays(100),
                now,
                100,
                0,
                1);
        controlQuality.process(product);
        assertThat(warehouse.getProducts().isEmpty()).isTrue();
        assertThat(shop.getProducts().isEmpty()).isTrue();
        assertThat(trash.getProducts()).containsExactly(product);
    }
}

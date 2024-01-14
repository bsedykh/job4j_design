package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

public class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenSelectReturnsSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        var actualMenuItem = menu.select("Сходить в магазин");
        assertThat(actualMenuItem).isNotEmpty();
        assertThat(actualMenuItem.get()).isEqualTo(new Menu.MenuItemInfo(
                "Сходить в магазин",
                List.of("Купить продукты"),
                STUB_ACTION, "1.", 0));
        actualMenuItem = menu.select("Покормить собаку");
        assertThat(actualMenuItem).isNotEmpty();
        assertThat(actualMenuItem.get()).isEqualTo(new Menu.MenuItemInfo(
                "Покормить собаку",
                List.of(),
                STUB_ACTION, "2.", 0));
        actualMenuItem = menu.select("Купить продукты");
        assertThat(actualMenuItem).isNotEmpty();
        assertThat(actualMenuItem.get()).isEqualTo(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"),
                STUB_ACTION, "1.1.", 1));
        actualMenuItem = menu.select("Купить хлеб");
        assertThat(actualMenuItem).isNotEmpty();
        assertThat(actualMenuItem.get()).isEqualTo(new Menu.MenuItemInfo(
                "Купить хлеб",
                List.of(),
                STUB_ACTION, "1.1.1.", 2));
        actualMenuItem = menu.select("Купить молоко");
        assertThat(actualMenuItem).isNotEmpty();
        assertThat(actualMenuItem.get()).isEqualTo(new Menu.MenuItemInfo(
                "Купить молоко",
                List.of(),
                STUB_ACTION, "1.1.2.", 2));
    }

    @Test
    public void whenSelectMissingMenuItemThenEmptyOptional() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        assertThat(menu.select("Отсутствующий пункт меню")).isEmpty();
    }

    @Test
    public void whenIterateThroughMenu() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        var iterator = menu.iterator();
        assertThat(iterator.hasNext()).isTrue();
        var actualMenuItem = iterator.next();
        assertThat(actualMenuItem).isEqualTo(new Menu.MenuItemInfo(
                        "Сходить в магазин",
                        List.of("Купить продукты"),
                        STUB_ACTION, "1.", 0));
        assertThat(iterator.hasNext()).isTrue();
        actualMenuItem = iterator.next();
        assertThat(actualMenuItem).isEqualTo(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"),
                STUB_ACTION, "1.1.", 1));
        assertThat(iterator.hasNext()).isTrue();
        actualMenuItem = iterator.next();
        assertThat(actualMenuItem).isEqualTo(new Menu.MenuItemInfo(
                "Купить хлеб",
                List.of(),
                STUB_ACTION, "1.1.1.", 2));
        assertThat(iterator.hasNext()).isTrue();
        actualMenuItem = iterator.next();
        assertThat(actualMenuItem).isEqualTo(new Menu.MenuItemInfo(
                "Купить молоко",
                List.of(),
                STUB_ACTION, "1.1.2.", 2));
        assertThat(iterator.hasNext()).isTrue();
        actualMenuItem = iterator.next();
        assertThat(actualMenuItem).isEqualTo(new Menu.MenuItemInfo(
                "Покормить собаку",
                List.of(),
                STUB_ACTION, "2.", 0));
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void whenNextWithNoElementsThenException() {
        Menu menu = new SimpleMenu();
        assertThatThrownBy(() -> menu.iterator().next())
                .isInstanceOf(NoSuchElementException.class);
    }
}

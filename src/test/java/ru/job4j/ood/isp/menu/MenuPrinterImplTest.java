package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.*;

class MenuPrinterImplTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenPrint() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        MenuPrinter printer = new MenuPrinterImpl();
        var out = new StringOutput();
        printer.print(menu, out);
        var stringJoiner = new StringJoiner(System.lineSeparator(),
                "", System.lineSeparator());
        stringJoiner.add("1. Сходить в магазин");
        stringJoiner.add("---- 1.1. Купить продукты");
        stringJoiner.add("-------- 1.1.1. Купить хлеб");
        stringJoiner.add("-------- 1.1.2. Купить молоко");
        stringJoiner.add("2. Покормить собаку");
        assertThat(out.get()).isEqualTo(stringJoiner.toString());
    }
}

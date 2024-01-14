package ru.job4j.ood.isp.menu;

public class MenuPrinterImpl implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        for (var menuItem : menu) {
            System.out.printf(
                    "%s%s %s%n", menuItem.getLevel() == 0 ? ""
                            : "----".repeat(menuItem.getLevel()).concat(" "),
                    menuItem.getNumber(),
                    menuItem.getName());
        }
    }
}

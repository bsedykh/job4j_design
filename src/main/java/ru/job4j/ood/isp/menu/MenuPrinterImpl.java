package ru.job4j.ood.isp.menu;

public class MenuPrinterImpl implements MenuPrinter {
    @Override
    public void print(Menu menu, Output out) {
        for (var menuItem : menu) {
            out.print("%s%s %s%n".formatted(
                    menuItem.getLevel() == 0 ? ""
                            : "----".repeat(menuItem.getLevel()).concat(" "),
                    menuItem.getNumber(),
                    menuItem.getName()));
        }
    }
}

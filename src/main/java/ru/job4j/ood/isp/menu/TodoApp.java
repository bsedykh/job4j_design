package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TodoApp {
    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        var input = new Scanner(System.in);
        var output = new ConsoleOutput();
        boolean run = true;
        String name, parentName;
        while (run) {
            System.out.println("1 - Add root menu item");
            System.out.println("2 - Add child menu item");
            System.out.println("3 - Select menu item");
            System.out.println("4 - Print menu");
            System.out.println("Enter any other number for exit.");
            int select = Integer.parseInt(input.nextLine());
            switch (select) {
                case 1:
                    System.out.print("Enter item name: ");
                    name = input.nextLine();
                    menu.add(Menu.ROOT, name, DEFAULT_ACTION);
                    break;
                case 2:
                    System.out.print("Enter parent name: ");
                    parentName = input.nextLine();
                    System.out.print("Enter item name: ");
                    name = input.nextLine();
                    menu.add(parentName, name, DEFAULT_ACTION);
                    break;
                case 3:
                    System.out.print("Enter item name: ");
                    name = input.nextLine();
                    menu.select(name);
                    break;
                case 4:
                    MenuPrinter printer = new MenuPrinterImpl();
                    printer.print(menu, output);
                    break;
                default:
                    run = false;
            }
        }
    }
}

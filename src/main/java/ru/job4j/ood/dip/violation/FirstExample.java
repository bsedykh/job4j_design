package ru.job4j.ood.dip.violation;

class Book {
    private final String text = "some text";
    private final ConsolePrinter printer = new ConsolePrinter();

    public void print() {
        printer.print(text);
    }
}

class ConsolePrinter {
    public void print(String text) {
        System.out.print(text);
    }
}

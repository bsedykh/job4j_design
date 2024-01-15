package ru.job4j.ood.isp.menu;

public class ConsoleOutput implements Output {
    @Override
    public void print(String str) {
        System.out.print(str);
    }
}

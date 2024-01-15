package ru.job4j.ood.isp.menu;

public class StringOutput implements Output {
    private final StringBuilder buffer = new StringBuilder();

    @Override
    public void print(String str) {
        buffer.append(str);
    }

    public String get() {
        return buffer.toString();
    }
}

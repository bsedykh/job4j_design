package ru.job4j.ood.ocp.violation;

public class Parser {
    public void parse(Source source) {
        if ("file".equals(source.type)) {
            openFile();
        } else if ("db".equals(source.type)) {
            openDB();
        }
    }

    private void openFile() {
    }

    private void openDB() {
    }
}

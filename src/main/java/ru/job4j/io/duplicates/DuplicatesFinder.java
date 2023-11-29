package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        var duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("."), duplicatesVisitor);
        duplicatesVisitor.getDuplicates().entrySet().stream()
                        .filter(entry -> entry.getValue().size() > 1)
                        .forEach(entry -> {
                            System.out.println(entry.getKey());
                            entry.getValue().forEach(
                                    value -> System.out.printf("\t%s%n", value));
                        });
    }
}

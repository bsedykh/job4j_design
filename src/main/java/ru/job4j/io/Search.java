package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        search(Path.of(args[0]), p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    private static void validateArgs(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid args. Usage: <root path> <file extension>");
        }
        if (!Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException("Invalid args. The first argument must be a directory");
        }
        if (args[1].length() < 2 || !args[1].startsWith(".")) {
            throw new IllegalArgumentException(
                    "Invalid args. The second argument must contain at least two characters and start with '.'");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}

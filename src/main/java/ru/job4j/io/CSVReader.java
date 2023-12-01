package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    private static final Charset ENCODING = StandardCharsets.UTF_8;

    private record Args(Path source, Path target, String delimiter, String[] columns) {
    }

    public static void handle(ArgsName argsName) throws IOException {
        var args = parseArgs(argsName);
        var result = new ArrayList<String>();
        result.add(String.join(args.delimiter, args.columns));
        try (var scanner = new Scanner(args.source, ENCODING)) {
            var sourceColumns = scanner.nextLine().split(args.delimiter);
            var indexes = getIndexes(sourceColumns, args.columns);
            while (scanner.hasNextLine()) {
                var joiner = new StringJoiner(args.delimiter);
                var values = scanner.nextLine().split(args.delimiter);
                for (var index : indexes) {
                    joiner.add(values[index]);
                }
                result.add(joiner.toString());
            }
        }
        if ("stdout".equals(args.target.toString())) {
            result.forEach(System.out::println);
        } else {
            Files.write(args.target, result, ENCODING);
        }
    }

    private static int[] getIndexes(String[] sourceColumns, String[] targetColumns) {
        var indexes = new int[targetColumns.length];
        for (int i = 0; i < targetColumns.length; i++) {
            for (int j = 0; j < sourceColumns.length; j++) {
                if (targetColumns[i].equals(sourceColumns[j])) {
                    indexes[i] = j;
                }
            }
        }
        return indexes;
    }

    private static Args parseArgs(ArgsName argsName) {
        if (argsName.size() != 4) {
            throw new IllegalArgumentException(
                    "Usage: -path=<path> -delimiter=<delimiter> -out=<out> -filter=<filter>");
        }
        var source = Path.of(argsName.get("path"));
        if (!Files.isRegularFile(source)) {
            throw new IllegalArgumentException(
                    "File '%s' does not exist".formatted(source));
        }
        var target = Path.of(argsName.get("out"));
        var delimiter = argsName.get("delimiter");
        var columns = argsName.get("filter").split(",");
        return new Args(source, target, delimiter, columns);
    }

    public static void main(String[] args) throws IOException {
        handle(ArgsName.of(args));
    }
}

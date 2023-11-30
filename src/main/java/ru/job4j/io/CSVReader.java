package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    private record Args(Path source, Path target, String delimiter, List<String> columns) {
    }

    public static void handle(ArgsName argsName) throws IOException {
        var args = parseArgs(argsName);
        var indexes = getIndexes(args.source, args.delimiter, args.columns);
        try (var reader = Files.newBufferedReader(args.source, StandardCharsets.UTF_8);
             var writer = getWriter(args.target)) {
            String line;
            while ((line = reader.readLine()) != null) {
                var in = new Scanner(line).useDelimiter(args.delimiter);
                var pos = 0;
                var values = new String[indexes.size()];
                while (in.hasNext()) {
                    var value = in.next();
                    var index = indexes.get(pos);
                    if (index != null) {
                        values[index] = value;
                    }
                    pos++;
                }
                writer.write(String.join(args.delimiter, values)
                        .concat(System.lineSeparator()));
            }
        }
    }

    private static Map<Integer, Integer> getIndexes(Path source,
                                            String delimiter,
                                            List<String> columns) throws IOException {
        Map<Integer, Integer> indexes = Collections.emptyMap();
        try (var lines = Files.lines(source, StandardCharsets.UTF_8)) {
            var line = lines.findFirst();
            if (line.isPresent()) {
                indexes = getIndexes(line.get(), delimiter, columns);
            }
        }
        return indexes;
    }

    private static Map<Integer, Integer> getIndexes(String source,
                                            String delimiter,
                                            List<String> columns) {
        var indexes = new HashMap<Integer, Integer>();
        var in = new Scanner(source).useDelimiter(delimiter);
        var pos = 0;
        while (in.hasNext()) {
            var column = in.next();
            var index = columns.indexOf(column);
            if (index != -1) {
                indexes.put(pos, index);
            }
            pos++;
        }
        return indexes;
    }

    private static Writer getWriter(Path target) throws IOException {
        Writer writer;
        if ("stdout".equals(target.toString())) {
            writer = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        } else {
            writer = Files.newBufferedWriter(target, StandardCharsets.UTF_8);
        }
        return writer;
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
        var columns = List.of(argsName.get("filter").split(","));
        return new Args(source, target, delimiter, columns);
    }

    public static void main(String[] args) throws IOException {
        handle(ArgsName.of(args));
    }
}

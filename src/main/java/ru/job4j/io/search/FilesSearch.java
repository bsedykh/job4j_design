package ru.job4j.io.search;

import ru.job4j.io.ArgsName;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FilesSearch {
    private static class Params {
        final Path directory;
        final Pattern filename;
        final Path output;

        Params(String[] args) {
            var argsName = ArgsName.of(args);
            if (argsName.size() != 4) {
                throw new IllegalArgumentException(
                        "Usage: -d=<directory> -n=<filename> -t=<type> -o=<output>");
            }
            directory = Path.of(argsName.get("d"));
            if (!Files.isDirectory(directory)) {
                throw new IllegalArgumentException(
                        "Directory '%s' does not exist".formatted(directory));
            }
            var name = argsName.get("n");
            var type = argsName.get("t");
            if ("mask".equals(type)) {
                if (!name.matches("[\\w.*?]+")) {
                    throw new IllegalArgumentException(
                            "Invalid mask: '%s'".formatted(name));
                }
                filename = Pattern.compile(name
                        .replace(".", "\\.")
                        .replace("?", ".")
                        .replace("*", ".*"));
            } else if ("name".equals(type)) {
                if (!name.matches("[\\w.]+")) {
                    throw new IllegalArgumentException(
                            "Invalid filename: '%s'".formatted(name));
                }
                filename = Pattern.compile(name.replace(".", "\\."));
            } else if ("regex".equals(type)) {
                filename = Pattern.compile(name);
            } else {
                throw new IllegalArgumentException(
                        "Unknown search type: '%s'.".formatted(type)
                                + " Allowed values are: 'mask', 'name', 'regex'");
            }
            output = Path.of(argsName.get("o"));
        }
    }

    public static void main(String[] args) throws IOException {
        var params = new Params(args);
        List<String> result;
        try (var files = Files.walk(params.directory)) {
            result = files
                    .filter(file -> {
                        var matcher = params.filename.matcher(file.getFileName().toString());
                        return matcher.matches();
                    })
                    .map(file -> file.normalize().toAbsolutePath().toString())
                    .collect(Collectors.toList());
        }
        Files.write(params.output, result, StandardCharsets.UTF_8);
    }
}

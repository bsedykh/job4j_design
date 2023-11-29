package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '%s' is missing".formatted(key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (var arg : args) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException(
                        "Error: This argument '%s' does not start with a '-' character".formatted(arg));
            }
            var keyValue = arg.substring(1).split("=", 2);
            validateKeyValue(keyValue, arg);
            values.put(keyValue[0], keyValue[1]);
        }
    }

    private void validateKeyValue(String[] keyValue, String arg) {
        if (keyValue.length < 2) {
            throw new IllegalArgumentException(
                    "Error: This argument '%s' does not contain an equal sign".formatted(arg));
        }
        if (keyValue[0].isEmpty()) {
            throw new IllegalArgumentException(
                    "Error: This argument '%s' does not contain a key".formatted(arg));
        }
        if (keyValue[1].isEmpty()) {
            throw new IllegalArgumentException(
                    "Error: This argument '%s' does not contain a value".formatted(arg));
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}

package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (var source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(ArgsName args) {
        var usage = "Usage: -d=<directory> -e=<exclude> -o=<output>";
        if (args.size() < 3) {
            throw new IllegalArgumentException(usage);
        }
        String directory, exclude, output;
        try {
            directory = args.get("d");
            exclude = args.get("e");
            output = args.get("o");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("%s. %s".formatted(e.getMessage(), usage));
        }
        if (!Files.isDirectory(Path.of(directory))) {
            throw new IllegalArgumentException("The first argument must be a directory");
        }
        if (exclude.length() < 2 || !exclude.startsWith(".")) {
            throw new IllegalArgumentException(
                    "The second argument must contain at least two characters and start with '.'");
        }
        if (output.length() < 5 || !output.endsWith(".zip")) {
            throw new IllegalArgumentException(
                    "The third argument must contain at least five characters and end with '.zip'");
        }
    }

    public static void main(String[] args) throws IOException {
        var parsedArgs = ArgsName.of(args);
        validateArgs(parsedArgs);
        var sources = Search.search(Path.of(parsedArgs.get("d")),
                path -> !path.getFileName().toString().endsWith(parsedArgs.get("e")));
        Zip zip = new Zip();
        zip.packFiles(sources, new File(parsedArgs.get("o")));
    }
}

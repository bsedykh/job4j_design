package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<String>> duplicates = new HashMap<>();

    public Map<FileProperty, List<String>> getDuplicates() {
        return duplicates;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        var key = new FileProperty(attrs.size(), file.getFileName().toString());
        var path = file.normalize().toAbsolutePath().toString();
        var paths = duplicates.get(key);
        if (paths == null) {
            var list = new ArrayList<String>();
            list.add(path);
            duplicates.put(key, list);
        } else {
            paths.add(path);
        }
        return super.visitFile(file, attrs);
    }
}

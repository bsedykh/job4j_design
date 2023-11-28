package ru.job4j.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {
    private File source;
    private File target;

    @BeforeEach
    void init(@TempDir Path tempDir) {
        source = tempDir.resolve("source.txt").toFile();
        target = tempDir.resolve("target.txt").toFile();
    }

    private void writeToSource(List<String> lines) throws IOException {
        try (PrintWriter out = new PrintWriter(source)) {
            lines.forEach(out::println);
        }
    }

    private List<String> readFromTarget() throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            return in.lines().collect(Collectors.toList());
        }
    }

    @Test
    void whenNoPeriods() throws IOException {
        writeToSource(List.of(
                "200 10:56:01",
                "300 10:59:01",
                "200 11:02:02"
        ));
        List<String> expected = Collections.emptyList();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        assertThat(readFromTarget()).isEqualTo(expected);
    }

    @Test
    void whenOnlyPeriod() throws IOException {
        writeToSource(List.of(
                "500 10:00:01",
                "400 10:30:01",
                "500 10:56:01"
        ));
        List<String> expected = Collections.emptyList();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        assertThat(readFromTarget()).isEqualTo(expected);
    }

    @Test
    void whenPeriodInTheBeginning() throws IOException {
        writeToSource(List.of(
                "500 10:00:01",
                "400 10:30:01",
                "200 10:56:01",
                "300 10:59:01",
                "200 11:02:02"
        ));
        List<String> expected = List.of("10:00:01;10:56:01");
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        assertThat(readFromTarget()).isEqualTo(expected);
    }

    @Test
    void whenPeriodInTheMiddle() throws IOException {
        writeToSource(List.of(
                "200 10:00:01",
                "200 10:30:01",
                "500 10:56:01",
                "500 10:59:01",
                "200 11:02:02"
        ));
        List<String> expected = List.of("10:56:01;11:02:02");
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        assertThat(readFromTarget()).isEqualTo(expected);
    }

    @Test
    void whenPeriodInTheEnd() throws IOException {
        writeToSource(List.of(
                "200 10:00:01",
                "200 10:30:01",
                "500 10:56:01",
                "500 10:59:01",
                "500 11:02:02"
        ));
        List<String> expected = Collections.emptyList();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        assertThat(readFromTarget()).isEqualTo(expected);
    }

    @Test
    void whenMultiplePeriods() throws IOException {
        writeToSource(List.of(
                "200 10:56:01",
                "500 10:57:01",
                "400 10:58:01",
                "300 10:59:01",
                "500 11:01:02",
                "200 11:02:02"
        ));
        List<String> expected = List.of(
                "10:57:01;10:59:01",
                "11:01:02;11:02:02"
        );
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        assertThat(readFromTarget()).isEqualTo(expected);
    }
}

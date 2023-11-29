package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        var phrases = readPhrases();
        var log = new ArrayList<String>();
        var random = new Random();
        var in = new Scanner(System.in);
        var stop = false;
        String input;
        do {
            input = in.nextLine();
            log.add(input);
            if (STOP.equals(input) || OUT.equals(input)) {
                stop = true;
            } else if (CONTINUE.equals(input)) {
                stop = false;
            }
            if (!stop) {
                var answer = phrases.get(random.nextInt(phrases.size()));
                System.out.println(answer);
                log.add(answer);
            }
        } while (!OUT.equals(input));
        saveLog(log);
    }

    private List<String> readPhrases() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.toList());
        }
    }

    private void saveLog(List<String> log) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            log.forEach(writer::println);
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("data/log.txt", "data/answers.txt");
        cc.run();
    }
}

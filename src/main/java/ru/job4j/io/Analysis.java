package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
            String start = null;
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                String[] entry = line.split(" ");
                if (start == null && isError(entry[0])) {
                    start = entry[1];
                } else if (start != null && !isError(entry[0])) {
                    write.println(start + ";" + entry[1]);
                    start = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isError(String code) {
        return "400".equals(code) || "500".equals(code);
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}

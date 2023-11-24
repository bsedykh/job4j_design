package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class MultipleToFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/multiple.txt")) {
            for (int i = 2; i <= 9; i++) {
                out.write("1 * %d = %d".formatted(i, i).getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

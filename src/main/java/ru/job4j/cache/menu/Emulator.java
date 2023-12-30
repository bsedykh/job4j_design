package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {
    private static final Integer SET_DIR = 1;
    private static final Integer PUT_FILE = 2;
    private static final Integer GET_FILE = 3;
    private static final String MENU = """
                Введите 1 для указания директории.
                Введите 2 для загрузки файла в кэш.
                Введите 3 для получения содержимого файла из кэша.
                Введите любое другое число для выхода.
            """;
    private static final String SELECT = "Выберите меню";
    private static final String SET_DIR_TEXT = "Введите путь к каталогу";
    private static final String FILE_TEXT = "Введите имя файла";
    private static final String EXIT = "Конец работы";

    public static void main(String[] args) {
        DirFileCache cache = null;
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (SET_DIR == userChoice) {
                System.out.println(SET_DIR_TEXT);
                String text = scanner.nextLine();
                cache = new DirFileCache(text);
            } else if (PUT_FILE == userChoice) {
                System.out.println(FILE_TEXT);
                String text = scanner.nextLine();
                cache.get(text);
            } else if (GET_FILE == userChoice) {
                System.out.println(FILE_TEXT);
                String text = scanner.nextLine();
                System.out.println(cache.get(text));
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }
}

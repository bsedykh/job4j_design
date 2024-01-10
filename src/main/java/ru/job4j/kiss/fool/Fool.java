package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {
    public static String getAnswer(int number) {
        String answer;
        if (number % 3 == 0 && number % 5 == 0) {
            answer = "FizzBuzz";
        } else if (number % 3 == 0) {
            answer = "Fizz";
        } else if (number % 5 == 0) {
            answer = "Buzz";
        } else {
            answer = String.valueOf(number);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(getAnswer(startAt));
            startAt++;
            var answer = input.nextLine();
            if (!getAnswer(startAt).equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }
}

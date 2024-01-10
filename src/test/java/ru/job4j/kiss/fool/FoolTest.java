package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {
    @Test
    void whenMultipleOf3ThenFizz() {
        var expected = "Fizz";
        var answer = Fool.getAnswer(9);
        assertThat(answer).isEqualTo(expected);
    }

    @Test
    void whenMultipleOf5ThenBuzz() {
        var expected = "Buzz";
        var answer = Fool.getAnswer(10);
        assertThat(answer).isEqualTo(expected);
    }

    @Test
    void whenMultipleOf3And5ThenFizzBuzz() {
        var expected = "FizzBuzz";
        var answer = Fool.getAnswer(15);
        assertThat(answer).isEqualTo(expected);
    }

    @Test
    void whenNotMultipleOf3Or5ThenNumber() {
        var expected = "17";
        var answer = Fool.getAnswer(17);
        assertThat(answer).isEqualTo(expected);
    }
}

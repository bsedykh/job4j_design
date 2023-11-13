package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        var list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list)
                .containsExactly("first", "second", "three", "four", "five")
                .containsSequence("second", "three");
        assertThat(list).first().isEqualTo("first");
        assertThat(list).last().isEqualTo("five");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        var set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set)
                .containsOnly("first", "second", "three", "four", "five")
                .allSatisfy(e -> {
                    assertThat(e.length()).isGreaterThan(3);
                    assertThat(e.length()).isLessThan(10);
                });
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        var map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map)
                .containsOnlyKeys("first", "second", "three", "four", "five")
                .containsValues(0, 1, 2, 3, 4)
                .containsEntry("first", 0);
    }
}

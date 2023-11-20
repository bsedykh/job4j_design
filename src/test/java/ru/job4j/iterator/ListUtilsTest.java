package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeFirst() {
        ListUtils.addBefore(input, 0, 0);
        assertThat(input).hasSize(3).containsSequence(0, 1, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterLast() {
        ListUtils.addAfter(input, 1, 4);
        assertThat(input).hasSize(3).containsSequence(1, 3, 4);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeIf(input, elem -> elem == 2);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenRemoveIfFirst() {
        ListUtils.removeIf(input, elem -> elem == 1);
        assertThat(input).hasSize(1).containsSequence(3);
    }

    @Test
    void whenRemoveIfLast() {
        ListUtils.removeIf(input, elem -> elem == 3);
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void whenRemoveIfAll() {
        ListUtils.removeIf(input, elem -> true);
        assertThat(input).hasSize(0);
    }

    @Test
    void whenRemoveIfNone() {
        ListUtils.removeIf(input, elem -> false);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenRemoveAll() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListUtils.removeAll(input, List.of(2, 3, 4));
        assertThat(input).hasSize(2).containsSequence(1, 5);
    }

    @Test
    void whenReplaceIf() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListUtils.replaceIf(input, elem -> elem % 2 == 0, -1);
        assertThat(input).hasSize(5).containsSequence(1, -1, 3, -1, 5);
    }
}

package ru.job4j.set;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleArraySetTest {
    private final SimpleSet<Integer> set = new SimpleArraySet<>();

    @Test
    void whenAddNonNull() {
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddSomeElementsNonNull() {
        assertThat(set.contains(1)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set.contains(2)).isFalse();
        assertThat(set.add(2)).isTrue();
        assertThat(set.contains(2)).isTrue();
        assertThat(set.add(2)).isFalse();
        assertThat(set.contains(3)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set.add(3)).isFalse();
    }

    @Test
    void whenAddNull() {
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenEmptySetThenHasNextIsFalse() {
        assertThat(set.iterator().hasNext()).isFalse();
    }

    @Test
    void whenEmptySetThenNextThrows() {
        assertThatThrownBy(() -> set.iterator().next())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenIterateOverSetWithSomeValues() {
        set.add(1);
        set.add(2);
        set.add(3);
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void whenIteratorDoesNotMoveThenHasNextReturnsSameValue() {
        set.add(1);
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        set.add(1);
        assertThat(set.iterator().next()).isEqualTo(1);
        assertThat(set.iterator().next()).isEqualTo(1);
    }

    @Test
    void whenAddAfterGetIteratorThenHasNextThrows() {
        Iterator<Integer> iterator = set.iterator();
        set.add(1);
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenAddAfterGetIteratorThenNextThrows() {
        Iterator<Integer> iterator = set.iterator();
        set.add(1);
        assertThatThrownBy(iterator::next)
                .isInstanceOf(ConcurrentModificationException.class);
    }
}

package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkMissingInput() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void checkMissingSeparator() {
        NameLoad nameLoad = new NameLoad();
        var input = "keyvalue";
        assertThatThrownBy(() -> nameLoad.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(input);
    }

    @Test
    void checkMissingKey() {
        NameLoad nameLoad = new NameLoad();
        var input = "=value";
        assertThatThrownBy(() -> nameLoad.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(input);
    }

    @Test
    void checkMissingValue() {
        NameLoad nameLoad = new NameLoad();
        var input = "key=";
        assertThatThrownBy(() -> nameLoad.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(input);
    }
}

package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1")).isEqualTo("value1");
    }

    @Test
    void whenPairWithEmptyStrings() {
        String path = "./data/pair_with_empty_strings.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1")).isEqualTo("value1");
    }

    @Test
    void whenPairWithComments() {
        String path = "./data/pair_with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1")).isEqualTo("value1");
    }

    @Test
    void whenMultiplePair() {
        String path = "./data/multiple_pair.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1")).isEqualTo("value1");
        assertThat(config.value("key2")).isEqualTo("value2");
        assertThat(config.value("key3")).isEqualTo("value3");
    }

    @Test
    void whenEmptyKey() {
        String path = "./data/empty_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenEmptyValue() {
        String path = "./data/empty_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenEmptyDelimiter() {
        String path = "./data/empty_delimiter.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenEmptyKeyValue() {
        String path = "./data/empty_key_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMultipleDelimiter() {
        String path = "./data/multiple_delimiter.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1")).isEqualTo("value=1");
        assertThat(config.value("key2")).isEqualTo("value=");
    }
}

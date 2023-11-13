package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void whenZeroVertexesThenSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void whenFourVertexesThenTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void whenEightVertexesThenCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void whenTwoVertexesThenUnknown() {
        Box box = new Box(2, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void whenZeroEdgeThenUnknown() {
        Box box = new Box(4, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void whenKnownObjectThenNumberOfVerticesIsNotChangedAfterInit() {
        Box box = new Box(4, 10);
        var result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(4);
    }

    @Test
    void whenUnknownObjectThenNumberOfVerticesIsMinusOne() {
        Box box = new Box(2, 10);
        var result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(-1);
    }

    @Test
    void whenKnownObjectThenExists() {
        Box box = new Box(4, 10);
        var result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void whenUnknownObjectThenNotExists() {
        Box box = new Box(2, 10);
        var result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void whenGetAreaForCube() {
        Box box = new Box(8, 1);
        var result = box.getArea();
        assertThat(result).isCloseTo(6.0, withPrecision(0.01));
    }

    @Test
    void whenGetAreaForUnknown() {
        Box box = new Box(2, 1);
        var result = box.getArea();
        assertThat(result).isCloseTo(0.0, withPrecision(0.01));
    }
}

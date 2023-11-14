package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        while (row < data.length) {
            if (column < data[row].length) {
                result = true;
                break;
            }
            row++;
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        var result = data[row][column];
        column++;
        if (column == data[row].length) {
            row++;
            column = 0;
        }
        return result;
    }
}

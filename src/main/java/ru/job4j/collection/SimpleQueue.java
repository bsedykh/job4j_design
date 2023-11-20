package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inSize;
    private int outSize;

    public T poll() {
        if (outSize == 0 && inSize == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (outSize == 0) {
            for (int i = 0; i < inSize; i++) {
                out.push(in.pop());
            }
            outSize = inSize;
            inSize = 0;
        }
        var value = out.pop();
        outSize--;
        return value;
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }
}

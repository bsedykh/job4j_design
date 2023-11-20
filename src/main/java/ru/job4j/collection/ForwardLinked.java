package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    private Node<T> getNode(int index) {
        var node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public void add(T value) {
        var node = new Node<>(value, null);
        if (head == null) {
            head = node;
        } else {
            var tail = getNode(size - 1);
            tail.next = node;
        }
        modCount++;
        size++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return getNode(index).item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        var item = head.item;
        var next = head.next;
        head.item = null;
        head.next = null;
        head = next;
        modCount++;
        size--;
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> cursor = head;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                var node = cursor;
                cursor = cursor.next;
                return node.item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}

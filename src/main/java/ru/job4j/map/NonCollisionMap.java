package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    @SuppressWarnings("unchecked")
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count + 1 > LOAD_FACTOR * capacity) {
            expand();
        }
        var result = false;
        var hashCode = Objects.hashCode(key);
        var index = indexFor(hashCode);
        if (table[index] == null) {
            table[index] = new MapEntry<>(hashCode, key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hashCode) {
        return hash(hashCode) & (capacity - 1);
    }

    @SuppressWarnings("unchecked")
    private void expand() {
        var newCapacity = capacity * 2;
        var newTable = new MapEntry[newCapacity];
        capacity = newCapacity;
        for (var entry : table) {
            if (entry != null) {
                var index = indexFor(entry.hashCode);
                newTable[index] = entry;
            }
        }
        table = newTable;
    }

    private int indexOf(K key) {
        var result = -1;
        var hashCode = Objects.hashCode(key);
        var index = indexFor(hashCode);
        var entry = table[index];
        if (entry != null
                && entry.hashCode == hashCode && Objects.equals(entry.key, key)) {
            result = index;
        }
        return result;
    }

    @Override
    public V get(K key) {
        var index = indexOf(key);
        return index == -1 ? null : table[index].value;
    }

    @Override
    public boolean remove(K key) {
        var index = indexOf(key);
        if (index != -1) {
            table[index] = null;
            count--;
            modCount++;
        }
        return index != -1;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int index;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        int hashCode;
        K key;
        V value;

        public MapEntry(int hashCode, K key, V value) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }
    }
}

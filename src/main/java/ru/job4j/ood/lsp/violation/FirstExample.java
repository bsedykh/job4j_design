package ru.job4j.ood.lsp.violation;

interface Collection {
    int count();
}

class List implements Collection {
    @Override
    public int count() {
        return 0;
    }
}

class Set implements Collection {
    @Override
    public int count() {
        throw new UnsupportedOperationException();
    }
}

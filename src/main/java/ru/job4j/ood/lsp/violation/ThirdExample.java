package ru.job4j.ood.lsp.violation;

class DataSource {
}

class Database extends DataSource {
}

class File extends DataSource {
}

class Memory extends DataSource {
}

class ThirdExample {
    public void read(DataSource source) {
        if (source instanceof Database) {
            readFromDatabase();
        } else if (source instanceof File) {
            readFromFile();
        } else if (source instanceof Memory) {
            readFromMemory();
        }
    }

    private void readFromDatabase() {
    }

    private void readFromFile() {
    }

    private void readFromMemory() {
    }
}

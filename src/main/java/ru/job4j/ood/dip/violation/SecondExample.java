package ru.job4j.ood.dip.violation;

class PostgreSQLConnection {
}

class Service {
    private final PostgreSQLConnection connection = new PostgreSQLConnection();
}

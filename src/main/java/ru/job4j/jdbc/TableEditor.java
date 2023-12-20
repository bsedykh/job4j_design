package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        String driver = properties.getProperty("driver_class");
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
    }

    private void executeStatement(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws SQLException {
        executeStatement(String.format(
                "CREATE TABLE IF NOT EXISTS %s();",
                tableName
        ));
    }

    public void dropTable(String tableName) throws SQLException {
        executeStatement(String.format(
                "DROP TABLE IF EXISTS %s;",
                tableName
        ));
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        executeStatement(String.format(
                "ALTER TABLE IF EXISTS %s ADD COLUMN %s %s;",
                tableName,
                columnName,
                type
        ));
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        executeStatement(String.format(
                "ALTER TABLE IF EXISTS %s DROP COLUMN %s;",
                tableName,
                columnName
        ));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        executeStatement(String.format(
                "ALTER TABLE IF EXISTS %s RENAME COLUMN %s TO %s;",
                tableName,
                columnName,
                newColumnName
        ));
    }

    public String getTableScheme(String tableName) throws SQLException {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        try (TableEditor editor = new TableEditor(config)) {
            String table = "demo_table";
            editor.createTable(table);
            System.out.println(editor.getTableScheme(table));
            editor.addColumn(table, "column1", "text");
            editor.addColumn(table, "column2", "text");
            System.out.println(editor.getTableScheme(table));
            editor.renameColumn(table, "column2", "new_column2");
            System.out.println(editor.getTableScheme(table));
            editor.dropColumn(table, "new_column2");
            System.out.println(editor.getTableScheme(table));
            editor.dropTable(table);
        }
    }
}

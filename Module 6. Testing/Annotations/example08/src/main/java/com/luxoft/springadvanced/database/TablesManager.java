package com.luxoft.springadvanced.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TablesManager {

    public static void createTable(Connection connection) {
        String sql = "CREATE TABLE IF NOT EXISTS PERSONS (ID VARCHAR(50), " +
                "NAME VARCHAR(50));";

        executeStatement(connection, sql);
    }

    public static void dropTable(Connection connection) {
        String sql = "DROP TABLE IF EXISTS PERSONS;";

        executeStatement(connection, sql);
    }

    private static void executeStatement(Connection connection, String sql) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

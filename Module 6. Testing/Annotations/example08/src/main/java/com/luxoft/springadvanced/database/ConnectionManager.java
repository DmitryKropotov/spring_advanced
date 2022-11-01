package com.luxoft.springadvanced.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static Connection connection;

    public static Connection getConnection() {
        if (null != connection) {
            return connection;
        }
        return openConnection();
    }

    private static Connection openConnection() {

        try {
            Class.forName("org.h2.Driver"); // driver for H2
            connection = DriverManager.getConnection("jdbc:h2:~/person",
                    "sa",
                    ""
            );
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection() {
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}


package com.luxoft.springadvanced.listeners;

import com.luxoft.springadvanced.database.TablesManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import com.luxoft.springadvanced.database.ConnectionManager;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class DatabaseOperationsListener implements TestExecutionListener {

    private Connection connection;
    private Savepoint savepoint;

    @Override
    public void beforeTestClass(TestContext testContext) {
        connection = ConnectionManager.getConnection();
        TablesManager.dropTable(connection);
        TablesManager.createTable(connection);
    }

    @Override
    public void afterTestClass(TestContext testContext) {
        ConnectionManager.closeConnection();
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws SQLException {
        connection.setAutoCommit(false);
        savepoint = connection.setSavepoint("savepoint");
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws SQLException {
        connection.rollback(savepoint);
    }


}

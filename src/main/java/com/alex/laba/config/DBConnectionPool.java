package com.alex.laba.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnectionPool {
    private static final int POOL_SIZE = 4;
    private String url = "jdbc:mysql://localhost/mydb";
    private String user = "laba_user";
    private String password = "laba_password";
    private List<Connection> connections = new ArrayList<>();
    private List<Connection> usedConnection = new ArrayList<>();

    private static DBConnectionPool instance;

    private DBConnectionPool() throws SQLException, ClassNotFoundException {
        for (int i = 0; i < POOL_SIZE; i++) {
            connections.add(createConnection());
        }
    }

    public static DBConnectionPool getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new DBConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = connections
                .remove(connections.size() - 1);
        usedConnection.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        connections.add(connection);
        return usedConnection.remove(connection);
    }

    private Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}

package com.alex.laba.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    private String url = "jdbc:mysql://localhost/mydb";
    private String user = "laba_user";
    private String password = "laba_password";
    private Connection connection;

    private static DBConfig instance;

    private DBConfig() {
    }

    public static DBConfig getInstance() {
        if (instance == null) {
            instance = new DBConfig();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null) {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager
                        .getConnection(url, user, password);
            }
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("db connection problem");
        }

    }
}

package dev.clerdmy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String URL = Config.get("db.url");
    private static final String USER = Config.get("db.username");
    private static final String PASSWORD = Config.get("db.password");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
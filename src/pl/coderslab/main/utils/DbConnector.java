package pl.coderslab.main.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

    private static final String URL = "jdbc:mysql://lochalhost:3306/workshop_2?useSSL=false&serverTimezone=UTC&encodingCharacterset=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "coderslab";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
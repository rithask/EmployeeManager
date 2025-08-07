package com.litmus7.employeemanager.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionUtil {

    private static String JDBC_URL;
    private static String JDBC_USER;
    private static String JDBC_PASSWORD;

    static {
        JDBC_URL = "jdbc:mysql://localhost:3306/empdb";
        JDBC_USER = "root";
        JDBC_PASSWORD = "roottoor";
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

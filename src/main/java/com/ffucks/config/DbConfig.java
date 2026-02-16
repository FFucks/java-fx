package com.ffucks.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbConfig {

    private static final String URL = "jdbc:h2:./data/contactdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS contacts (
                    id IDENTITY PRIMARY KEY,
                    name VARCHAR(100),
                    phone VARCHAR(50),
                    email VARCHAR(100)
                )
            """);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

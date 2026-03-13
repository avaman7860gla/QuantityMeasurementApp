package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {

    public static Connection getConnection() {

        try {

            Class.forName(ApplicationConfig.getProperty("db.driver"));

            return DriverManager.getConnection(
                    ApplicationConfig.getProperty("db.url"),
                    ApplicationConfig.getProperty("db.username"),
                    ApplicationConfig.getProperty("db.password")
            );

        } catch (Exception e) {
            throw new RuntimeException("Database connection failed", e);
        }
    }
}
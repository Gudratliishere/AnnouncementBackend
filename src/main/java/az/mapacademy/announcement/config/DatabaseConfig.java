package az.mapacademy.announcement.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author : Dunay Gudratli
 * @since : 11.02.2025
 **/
public class DatabaseConfig {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/announcement_backend";
        String username = "root";
        String password = "root";

        return DriverManager.getConnection(url, username, password);
    }
}

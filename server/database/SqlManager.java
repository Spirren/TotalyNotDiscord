package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlManager {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
    }

    public static Connection getListenerConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.USER,
                DatabaseConfig.PASSWORD);
        conn.setReadOnly(true);
        return conn;
    }
}
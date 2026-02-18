package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import server.database.interfaces.IListenerConnectionProvider;
import server.database.interfaces.IConnectionProvider;

public class PostgresConnectionProvider implements IListenerConnectionProvider, IConnectionProvider {
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
    }
    
    public Connection getListenerConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.USER,
                DatabaseConfig.PASSWORD);
        conn.setReadOnly(true);
        return conn;
    }
}
package server.database.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface IListenerConnectionProvider {
    public Connection getListenerConnection() throws SQLException;
}
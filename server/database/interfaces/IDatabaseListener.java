package server.database.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabaseListener {
    public void addListener(Connection conn, String channel) throws SQLException;
}
package server.database.interfaces;

import java.sql.SQLException;

public interface IDatabaseListener {
    public void addListener(String channel) throws SQLException;
}
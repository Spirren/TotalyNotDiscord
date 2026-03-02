package server.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import server.database.interfaces.IDatabaseListener;

public class DatabaseListener implements IDatabaseListener {
    Connection conn;

    public DatabaseListener(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addListener(String channel) throws SQLException {
        try (Statement stmt = this.conn.createStatement()) {
            stmt.execute("LISTEN " + channel);
        }
    }
}

package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres",
                "postgres", "postgres")) {
            SqlConnection.viewTable(conn, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

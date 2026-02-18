package server.database;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import server.database.interfaces.IDatabaseListener;

public class SqlUtils implements IDatabaseListener {
    public static void viewTable(Connection conn, int chatId) throws SQLException {
        String query = "select * FROM fullchat WHERE chatId = ?;";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, chatId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String content = rs.getString("content");
                Timestamp timeSent = rs.getTimestamp("timeSent");
                Timestamp lastEdited = rs.getTimestamp("lastEdited");
                String username = rs.getString("username");
                System.out.println(content + ", " + timeSent + ", " + lastEdited +
                        ", " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> getUsers(Connection conn, int chatId) throws SQLException {
        String query = "select * FROM ChatMembers WHERE chatId = ?;";
        ArrayList<Integer> usersList = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, chatId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                usersList.add(rs.getInt("userId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void addListener(Connection conn, String channel) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("LISTEN " + channel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

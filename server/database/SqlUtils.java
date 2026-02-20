package server.database;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import resources.model.Message;
import resources.model.User;
import resources.model.interfaces.IUser;

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
            throw e;
        }
    }

    public static ArrayList<Integer> getUserIds(Connection conn, int chatId) throws SQLException {
        String query = "select * FROM ChatMembers WHERE chatId = ?;";
        ArrayList<Integer> usersIdList = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, chatId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                usersIdList.add(rs.getInt("userId"));
            }
        } catch (SQLException e) {
            throw e;
        }
        return usersIdList;
    }

    public static ArrayList<Integer> getChatIds(Connection conn, int userId) throws SQLException {
        String query = "select * FROM ChatMembers WHERE userId = ?;";
        ArrayList<Integer> chatIdList = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                chatIdList.add(rs.getInt("chatId"));
            }
        } catch (SQLException e) {
            throw e;
        }
        return chatIdList;
    }

    // public static ArrayList<String> getUserNamesInChat(Connection conn, int
    // chatId) throws SQLException {
    // // pass
    // }

    public static Message getMessage(Connection conn, int chatId, int messageIndex) throws SQLException {
        ArrayList<Message> messages = getMessages(conn, chatId, messageIndex, messageIndex + 1);
        if (messages.isEmpty()) {
            return null;
        }
        return messages.get(0);
    }

    public static ArrayList<Message> getMessages(Connection conn, int chatId, int messageStartIndex,
            int messageStopIndex) throws SQLException {
        String query = "select * FROM Messages  WHERE chatId = ? AND (messageIndex >= ? AND messageIndex < ?);";
        ArrayList<Message> messageList = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, chatId);
            pstmt.setInt(2, messageStartIndex);
            pstmt.setInt(3, messageStopIndex);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String content = rs.getString("content");
                LocalDateTime timeSent = rs.getObject("timeSent", LocalDateTime.class);
                LocalDateTime lastEdited = rs.getObject("lastEdited", LocalDateTime.class);
                User sender = getUser(conn, rs.getInt("userId"));
                int messageIndex = rs.getInt("messageIndex");
                messageList.add(new Message(timeSent, lastEdited, content, sender, messageIndex));
            }
        } catch (SQLException e) {
            throw e;
        }
        return messageList;
    }

    public static User getUser(Connection conn, int userId) throws SQLException {
        String query = "select * FROM Users WHERE userId = ?;";
        User user = null;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                LocalDate birthYear = rs.getObject("birthYear", LocalDate.class);
                int password = rs.getInt("password");

                user = new User(username, email, birthYear, userId, password);
            }
        } catch (SQLException e) {
            throw e;
        }
        return user;
    }

    public static void addMessage(Connection conn, Message message, int chatId) throws SQLException {
        String query = "INSERT INTO Messages VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, chatId);
            pstmt.setInt(2, ((IUser) message.getSender()).getID());
            pstmt.setInt(3, message.getIndex());
            pstmt.setString(4, message.getContent());
            pstmt.setObject(5, message.getTime());
            pstmt.setObject(6, message.getLastEdited());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void addListener(Connection conn, String channel) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("LISTEN " + channel);
        } catch (SQLException e) {
            throw e;
        }
    }
}

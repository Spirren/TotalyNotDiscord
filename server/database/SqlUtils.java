package server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

import resources.model.Chat;
import resources.model.Image;
import resources.model.Message;
import resources.model.User;
import resources.model.interfaces.IChat;
import resources.model.interfaces.IMessage;
import resources.model.interfaces.IUser;
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
        }
        return chatIdList;
    }

    public static Chat getChat(Connection conn, int chatId, int messageStartIndex,
            int messageStopIndex) throws SQLException {
        String query = "select * FROM Chats WHERE chatId = ?";
        Chat chat = null;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, chatId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int Id = rs.getInt("chatId");
                LocalDateTime timeCreated = rs.getObject("timeCreated", LocalDateTime.class);
                chat = new Chat(Id, timeCreated, getMessages(conn, chatId, messageStartIndex, messageStopIndex));
            }
        }
        return chat;
    }

    public static ArrayList<IChat> getUserChats(Connection conn, int userId) throws SQLException {
        ArrayList<IChat> chats = new ArrayList<>();
        for (Integer chatId : getChatIds(conn, userId)) {
            chats.add(getChat(conn, chatId, 0, 50));
        }
        if (chats.isEmpty()) {
            return null;
        }
        return chats;
    }

    public static void addMessage(Connection conn, Message message, int index) {System.out.println("Add test message");}
    public static void addMessage(Connection conn, Image message, int index) {System.out.println("Add image message");}

    // public static ArrayList<String> getUserNamesInChat(Connection conn, int
    // chatId) throws SQLException {
    // // pass
    // }

    public static IMessage getMessage(Connection conn, int chatId, int messageIndex) throws SQLException {
        LinkedList<IMessage> messages = getMessages(conn, chatId, messageIndex, messageIndex + 1);
        if (messages.isEmpty()) {
            return null;
        }
        return messages.get(0);
    }

    public static LinkedList<IMessage> getMessages(Connection conn, int chatId, int messageStartIndex,
            int messageStopIndex) throws SQLException {
        String query = "select * FROM messageswithnames  WHERE chatId = ? AND (messageIndex >= ? AND messageIndex < ?);";
        LinkedList<IMessage> messageList = new LinkedList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, chatId);
            pstmt.setInt(2, messageStartIndex);
            pstmt.setInt(3, messageStopIndex);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String content = rs.getString("content");
                LocalDateTime timeSent = rs.getObject("timeSent", LocalDateTime.class);
                LocalDateTime lastEdited = rs.getObject("lastEdited", LocalDateTime.class);
                User sender = getUser(conn, rs.getString("username"));
                int messageIndex = rs.getInt("messageIndex");
                messageList.add(new Message(timeSent, lastEdited, content, sender, messageIndex));
            }
        }
        return messageList;
    }

    public static User getUser(Connection conn, String userName) throws SQLException {
        String query = "select * FROM Users WHERE username = ?;";
        User user = null;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                LocalDate birthYear = rs.getObject("birthYear", LocalDate.class);
                int userId = rs.getInt("userId");

                user = new User(username, email, birthYear, userId);
            }
        }
        return user;
    }

    public static User getUser(Connection conn, int userid) throws SQLException {
        String query = "select * FROM Users WHERE userid = ?;";
        User user = null;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                LocalDate birthYear = rs.getObject("birthYear", LocalDate.class);
                int userId = rs.getInt("userId");

                user = new User(username, email, birthYear, userId);
            }
        }
        return user;
    }

    // public static void addMessage(Connection conn, IMessage message, int chatId) throws SQLException {
    //     String query = "INSERT INTO Messages VALUES (?, ?, ?, ?, ?, ?);";
    //     try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    //         pstmt.setInt(1, chatId);
    //         pstmt.setInt(2, ((IUser) message.getSender()).getID());
    //         pstmt.setInt(3, message.getIndex());
    //         pstmt.setString(4, message.getContent());
    //         pstmt.setObject(5, message.getTime());
    //         pstmt.setObject(6, message.getLastEdited());
    //         pstmt.executeUpdate();
    //     }
    // }

    public void addListener(Connection conn, String channel) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("LISTEN " + channel);
        }
    }
}

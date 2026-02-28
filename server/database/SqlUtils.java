package server.database;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import resources.model.Chat;
import resources.model.TextMessage;
import resources.model.User;
import resources.model.interfaces.IChat;
import resources.model.interfaces.IMessage;
import resources.model.interfaces.IUser;
import resources.model.interfaces.IImageMessage;
import resources.model.interfaces.ITextMessage;
import server.database.interfaces.IDatabaseListener;
import server.database.interfaces.IDatabaseOperator;

public class SqlUtils implements IDatabaseListener, IDatabaseOperator {
    Connection conn;

    public SqlUtils(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<Integer> getUserIds(int chatId) throws SQLException {
        String query = "select * FROM ChatMembers WHERE chatId = ?;";
        ArrayList<Integer> usersIdList = new ArrayList<>();
        try (PreparedStatement pstmt = this.conn.prepareStatement(query)) {
            pstmt.setInt(1, chatId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                usersIdList.add(rs.getInt("userId"));
            }
        }
        return usersIdList;
    }

    public ArrayList<Integer> getChatIds(int userId) throws SQLException {
        String query = "select * FROM ChatMembers WHERE userId = ?;";
        ArrayList<Integer> chatIdList = new ArrayList<>();
        try (PreparedStatement pstmt = this.conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                chatIdList.add(rs.getInt("chatId"));
            }
        }
        return chatIdList;
    }

    public Chat getChat(int chatId, int messageStartIndex,
            int messageStopIndex) throws SQLException {
        String query = "select * FROM Chats WHERE chatId = ?";
        Chat chat = null;
        try (PreparedStatement pstmt = this.conn.prepareStatement(query)) {
            pstmt.setInt(1, chatId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int Id = rs.getInt("chatId");
                LocalDateTime timeCreated = rs.getObject("timeCreated", LocalDateTime.class);
                chat = new Chat(Id, timeCreated, getMessages(chatId, messageStartIndex, messageStopIndex));
            }
        }
        return chat;
    }

    public ArrayList<IChat> getUserChats(int userId) throws SQLException {
        ArrayList<IChat> chats = new ArrayList<>();
        for (Integer chatId : getChatIds(userId)) {
            chats.add(getChat(chatId, 0, 50));
        }
        if (chats.isEmpty()) {
            return null;
        }
        return chats;
    }

    public IMessage getMessage(int chatId, int messageIndex) throws SQLException {
        LinkedList<IMessage> messages = getMessages(chatId, messageIndex, messageIndex + 1);
        if (messages.isEmpty()) {
            return null;
        }
        return messages.get(0);
    }

    public LinkedList<IMessage> getMessages(int chatId, int messageStartIndex,
            int messageStopIndex) throws SQLException {
        String query = "select * FROM messageswithnames  WHERE chatId = ? AND (messageIndex >= ? AND messageIndex < ?);";
        LinkedList<IMessage> messageList = new LinkedList<>();
        try (PreparedStatement pstmt = this.conn.prepareStatement(query)) {
            pstmt.setInt(1, chatId);
            pstmt.setInt(2, messageStartIndex);
            pstmt.setInt(3, messageStopIndex);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String content = rs.getString("textcontent");
                LocalDateTime timeSent = rs.getObject("timeSent", LocalDateTime.class);
                LocalDateTime lastEdited = rs.getObject("lastEdited", LocalDateTime.class);
                IUser sender = getUser(rs.getString("username"));
                int messageIndex = rs.getInt("messageIndex");
                int chatID = rs.getInt("chatID");
                messageList.add(new TextMessage(timeSent, lastEdited, content, sender, messageIndex, chatID));
            }
        }
        return messageList;
    }

    public IUser getUser(String userName) throws SQLException {
        String query = "select * FROM Users WHERE username = ?;";
        IUser user = null;
        try (PreparedStatement pstmt = this.conn.prepareStatement(query)) {
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

    public IUser getUser(int userid) throws SQLException {
        String query = "select * FROM Users WHERE userid = ?;";
        IUser user = null;
        try (PreparedStatement pstmt = this.conn.prepareStatement(query)) {
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

    // public static ArrayList<String> getUserNamesInChat( int
    // chatId) throws SQLException {
    // // pass
    // }

    public void addMessageContent(IImageMessage message, int chatId)
            throws SQLException, IOException {
        String query = "INSERT INTO TextMessages VALUES (?, ?, ?);";
        try (PreparedStatement pstmt = this.conn.prepareStatement(query)) {
            this.conn.setAutoCommit(false);
            addMessage(message, chatId);
            pstmt.setInt(1, chatId);
            pstmt.setInt(2, message.getIndex());
            pstmt.setBytes(3, toByteArray(message.getContent(), "png"));
            pstmt.executeUpdate();
            this.conn.commit();
        }
    }

    // should be moved to some utils function maybe
    public static byte[] toByteArray(BufferedImage bi, String format) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(bi, format, baos);
            return baos.toByteArray();
        }
    }

    public void addMessageContent(ITextMessage message, int chatId) throws SQLException {
        String query = "INSERT INTO TextMessages VALUES (?, ?, ?);";
        try (PreparedStatement pstmt = this.conn.prepareStatement(query)) {
            this.conn.setAutoCommit(false);
            addMessage(message, chatId);
            pstmt.setInt(1, chatId);
            pstmt.setInt(2, message.getIndex());
            pstmt.setString(3, message.getContent());
            pstmt.executeUpdate();
            this.conn.commit();
        }
    }

    public void addMessage(IMessage message, int chatId) throws SQLException {
        String query = "INSERT INTO Messages VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement pstmt = this.conn.prepareStatement(query)) {
            pstmt.setInt(1, chatId);
            pstmt.setInt(2, ((IUser) message.getSender()).getID());
            pstmt.setInt(3, message.getIndex());
            pstmt.setObject(4, message.getTime());
            pstmt.setObject(5, message.getLastEdited());
            pstmt.executeUpdate();
        }
    }

    public void addListener(String channel) throws SQLException {
        try (Statement stmt = this.conn.createStatement()) {
            stmt.execute("LISTEN " + channel);
        }
    }
}

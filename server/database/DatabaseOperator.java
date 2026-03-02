package server.database;

import java.util.ArrayList;
import java.util.LinkedList;

import resources.model.interfaces.IChat;
import resources.model.interfaces.IMessage;
import resources.model.interfaces.IUser;
import server.database.interfaces.IDatabaseOperator;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import resources.model.Chat;
import resources.model.interfaces.IImageMessage;
import resources.model.interfaces.ITextMessage;

public class DatabaseOperator implements IDatabaseOperator {
    private IDatabaseOperator databaseOperator;
    private static DatabaseOperator instance;


    private DatabaseOperator(IDatabaseOperator databaseOperator ) {
        this.databaseOperator = databaseOperator;
    }

    public static DatabaseOperator getInstance() {
        if (instance == null) {
            throw new IllegalStateException("DatabaseOperator must be initialized first!");
        }
        return instance;
    }

    public static void setInstance( IDatabaseOperator databaseOperator) {
        if (instance != null) {
            throw new IllegalStateException("DatabaseOperator already instantiated");
        }
        instance = new DatabaseOperator(databaseOperator);
    }

    public ArrayList<Integer> getUserIds(int chatId) throws SQLException {
        return databaseOperator.getUserIds(chatId);
    }

    public ArrayList<Integer> getChatIds(int userId) throws SQLException {
        return databaseOperator.getChatIds(userId);
    }

    public Chat getChat(int chatId, int messageStartIndex, int messageStopIndex) throws SQLException {
        return databaseOperator.getChat(chatId, messageStartIndex, messageStopIndex);
    }

    public ArrayList<IChat> getUserChats(int userId) throws SQLException {
        return databaseOperator.getUserChats(userId);
    }

    public IMessage getMessage(int chatId, int messageIndex) throws SQLException {
        return databaseOperator.getMessage(chatId, messageIndex);
    }

    public LinkedList<IMessage> getMessages(int chatId, int messageStartIndex, int messageStopIndex)
            throws SQLException {
        return databaseOperator.getMessages(chatId, messageStartIndex, messageStopIndex);
    }

    public BufferedImage getImageContent(int chatId, int messageIndex) throws SQLException {
        return databaseOperator.getImageContent(chatId, messageIndex);
    }

    public String getTextContent(int chatId, int messageIndex) throws SQLException {
        return databaseOperator.getTextContent(chatId, messageIndex);
    }

    public IUser getUser(String userName) throws SQLException {
        return databaseOperator.getUser(userName);
    }

    public IUser getUser(int userid) throws SQLException {
        return databaseOperator.getUser(userid);
    }

    public void addMessageContent(IImageMessage message, int chatId)
            throws SQLException, IOException {
        databaseOperator.addMessageContent(message, chatId);
    }

    public void addMessageContent(ITextMessage message, int chatId) throws SQLException {
        databaseOperator.addMessageContent(message, chatId);
    }

    public void addMessage(IMessage message, int chatId) throws SQLException {
        databaseOperator.addMessage(message, chatId);
    }

    @Override
    public int getLatestIndex(int chatID) throws SQLException {
        return databaseOperator.getLatestIndex(chatID);
    }

    @Override
    public LinkedList<IMessage> getNewestMessages(int chatId, int messageStartIndex,
            int messageStopIndex) throws SQLException {
        return databaseOperator.getNewestMessages(chatId, messageStartIndex, messageStopIndex);
    }
}

package server.database.interfaces;

import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.sql.SQLException;
import resources.model.Chat;
import resources.model.interfaces.IChat;
import resources.model.interfaces.IMessage;
import resources.model.interfaces.IUser;
import resources.model.interfaces.IImageMessage;
import resources.model.interfaces.ITextMessage;

public interface IDatabaseOperator {

        public ArrayList<Integer> getUserIds(int chatId) throws SQLException;

        public ArrayList<Integer> getChatIds(int userId) throws SQLException;

        public Chat getChat(int chatId, int messageStartIndex,
                        int messageStopIndex) throws SQLException;

        public ArrayList<IChat> getUserChats(int userId) throws SQLException;

        public IMessage getMessage(int chatId, int messageIndex) throws SQLException;

        public LinkedList<IMessage> getMessages(int chatId, int messageStartIndex,
                        int messageStopIndex) throws SQLException;

        public IUser getUser(String userName) throws SQLException;

        public IUser getUser(int userid) throws SQLException;

        public void addMessageContent(IImageMessage message, int chatId)
                        throws SQLException, IOException;

        public void addMessageContent(ITextMessage message, int chatId) throws SQLException;

        public void addMessage(IMessage message, int chatId) throws SQLException;

        BufferedImage getImageContent(int chatId, int messageIndex) throws SQLException;

        String getTextContent(int chatId, int messageIndex) throws SQLException;

        int getLatestIndex(int chatId) throws SQLException;

        public LinkedList<IMessage> getNewestMessages(int chatId, int messageStartIndex,
                        int messageStopIndex) throws SQLException;
}

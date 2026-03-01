package server.database;

import java.time.LocalDateTime;

import resources.model.ImageMessage;
import resources.model.TextMessage;
import resources.model.interfaces.IMessage;
import resources.model.interfaces.IUser;

import java.sql.SQLException;

public class MessageBuilder {
    public IMessage buildMessage(LocalDateTime timeSent, LocalDateTime lastEdited, IUser sender,
            int index, int chatID, String type) throws SQLException {
        DatabaseOperator databaseOperator = DatabaseOperator.getInstance();
        switch (type) {
            case "textMessage":
                return new TextMessage(timeSent, lastEdited, databaseOperator.getTextContent(chatID, index), sender,
                        index, chatID);
            case "imageMessage":
                return new ImageMessage(timeSent, lastEdited, databaseOperator.getImageContent(chatID, index), sender,
                        index, chatID);
            default:
                return null;
        }
    }
}

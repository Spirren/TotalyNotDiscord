package server.database;
import server.database.interfaces.IDatabaseOperator;

import java.sql.SQLException;
import java.util.ArrayList;

import resources.model.interfaces.IMessage;

public class MessageRetriever {
    private int msgIndex;
    private int chatId;

    public MessageRetriever(int chatI, int msgI) throws SQLException {
        msgIndex = msgI;
        chatId = chatI;
    }

    public void retrieveMessage() throws SQLException {
        IDatabaseOperator DBoperator = DatabaseOperator.getInstance();
        IMessage newMessage = DBoperator.getMessage(chatId, msgIndex);
        ArrayList<Integer> chatMembers = DBoperator.getUserIds(chatId);
        System.out.println("For subscriber retriever " + DatabaseHandler.getInstance().subscribers.get(3));
        DatabaseHandler.getInstance().notifySubscribers(chatMembers, newMessage);
    }

}

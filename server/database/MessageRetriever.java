package server.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import resources.model.interfaces.IMessage;

public class MessageRetriever{
    private int msgIndex;
    private int chatId;
    private Connection conn;

    public MessageRetriever(int chatI, int msgI)throws SQLException{
        msgIndex = msgI;
        chatId = chatI;
        conn = new PostgresConnectionProvider().getListenerConnection();
    }

    public void retrieveMessage()throws SQLException{
        IMessage newMessage = SqlUtils.getMessage(conn, chatId, msgIndex);
        ArrayList<Integer> chatMembers = SqlUtils.getUserIds(conn, chatId);
        System.out.println("For subscriber retriever " + DatabaseHandler.getInstance().subscribers.get(3));
        DatabaseHandler.getInstance().notifySubscribers(chatMembers, newMessage);
    } 

}

package server.database;
import java.sql.Connection;
import java.util.ArrayList;

import server.database.interfaces.IDataReceiver;

public class MessageDataReciver implements IDataReceiver {
    public void reciveData(int processId, String channelName, String payload) {
        String[] parts = payload.split("\\|");
        Integer chattId = Integer.parseInt(parts[0]);
        Integer messageIndex = Integer.parseInt(parts[1]);
        System.out.println("chatt id:" + chattId);
        System.out.println("messageIndex:" + messageIndex);
        Connection connect = new PostgresConnectionProvider().getListenerConnection();
        Message new_message = SqlUtils.getMessage(connect, chattId, messageIndex);
        ArrayList<Integer> chatMembers = SqlUtils.getUserIds(connect, chattId);
        MessageObserver.getInstance().notifySubscribers(chatMembers, new_message);
    }
}

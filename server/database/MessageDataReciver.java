package server.database;

import server.database.interfaces.IDataReceiver;

public class MessageDataReciver implements IDataReceiver {
    public void reciveData(int processId, String channelName, String payload) {
        String[] parts = payload.split("\\|");
        System.out.println("chatt id:" + parts[1]);
        System.out.println("messageIndex:" + parts[1]);
    }
}

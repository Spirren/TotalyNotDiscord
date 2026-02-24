package server.database;
import server.database.interfaces.IDataReceiver;

public class MessageDataReciver implements IDataReceiver {
    public void reciveData(int processId, String channelName, String payload) {
        String[] parts = payload.split("\\|");
        System.out.println("chatt id:" + parts[0]);
        System.out.println("messageIndex:" + parts[1]);
        new MessageRetriever(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])).retrieveMessage();
    }
}

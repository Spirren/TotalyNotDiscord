package server.database.interfaces;

public interface IDataReceiver {
    public void reciveData(int processId, String channelName, String payload);
}
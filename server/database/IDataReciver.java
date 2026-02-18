package server.database;

interface IDataReciver {
    public void reciveData(int processId, String channelName, String payload);
}
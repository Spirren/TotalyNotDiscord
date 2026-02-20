package server.database.interfaces;

public interface IEventStreamSubscriber {
    public void subscribe(String topic, IDataReceiver receiver);
}

package server.database;

import server.database.interfaces.IDataReceiver;
import server.database.interfaces.IEventStreamSubscriber;

public class StreamListener implements Runnable {
    IDataReceiver reciever;
    String channel;
    IEventStreamSubscriber subscriber;

    public StreamListener(IDataReceiver reciever, IEventStreamSubscriber subscriber, String channel) {
        this.reciever = reciever;
        this.channel = channel;
        this.subscriber = subscriber;
    }

    public void run() {
        subscriber.subscribe(channel, reciever);
    }
}

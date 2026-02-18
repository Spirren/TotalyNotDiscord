import java.util.LinkedList;

public class MessageObserver {
    private LinkedList<Subscriber> subscribers;

    public MessageObserver(){}
    
    public MessageObserver(LinkedList<Subscriber> s){
        subscribers = new LinkedList<Subscriber>(s);
    }

    public void subscribe(Subscriber s){
        subscribers.add(s);
    }

    public void unsubscribe(Subscriber s){
        subscribers.remove(s);
    }

    public void notifySubscribers(Message m){
        for(Subscriber s : subscribers){
            s.update(m);
        }
    }
}

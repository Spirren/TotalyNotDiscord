import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MessageObserver {
        HashMap<Integer, Subscriber> subscribers = new HashMap<>();

    public MessageObserver(){}
    
    public MessageObserver(Subscriber subs[]){
        for(Subscriber s : subs){
            subscribers.put(s.getSubKey(), s);
        }
    }

    public void subscribe(Subscriber s){
        subscribers.put(s.getSubKey(), s);
    }

    public void unsubscribe(Subscriber s){
        subscribers.remove(s.getSubKey());
    }

    public void notifySubscribers(int key[], IMessage m){
        for(int k : key){
            subscribers.get(k).update(m);
        }
    }
}

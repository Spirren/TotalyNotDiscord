import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MessageObserver {
    HashMap<Integer, ArrayList<Subscriber>> subscribers = new HashMap<>();

    public MessageObserver(){

    }
    
    public MessageObserver(ArrayList<Subscriber> subs){
        for(Subscriber s : subs){
            if(!subscribers.containsKey(s.getSubKey())){
            subscribers.put(s.getSubKey(), new ArrayList<Subscriber>());
            }
            subscribers.get(s.getSubKey()).add(s);
        }
    }

    public void subscribe(Subscriber s){
        if(!subscribers.containsKey(s.getSubKey())){
            subscribers.put(s.getSubKey(), new ArrayList<Subscriber>());
        }
        subscribers.get(s.getSubKey()).add(s);
    }

    public void unsubscribe(Subscriber s){
        subscribers.get(s.getSubKey()).remove(s);
        if(subscribers.get(s.getSubKey()).isEmpty()){
            subscribers.remove(s.getSubKey());
        }
    }

    public void notifySubscribers(ArrayList<Integer> key, IMessage m){
        for(int k : key){
            ArrayList<Subscriber> subs = subscribers.get(k);
            for(Subscriber s : subs){
                s.update(m);
            }
        }
    }
}

package resources.model;

import java.util.ArrayList;
import java.util.HashMap;

import resources.model.interfaces.*;

public class MessageHandler {
    private HashMap<Integer, Subscriber> subscribers = new HashMap<>();
    private IChatUpdateListener rerenderable;

    public MessageHandler(IChatUpdateListener rerenderable){
        this.rerenderable = rerenderable;
    }
    
    public MessageHandler(IChatUpdateListener rerenderable, ArrayList<Subscriber> subs){
        this.rerenderable = rerenderable;
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

    public void notifySubscribers(int key, IMessage m){
        subscribers.get(key).update(m);
        rerenderable.updateView();
    }
}

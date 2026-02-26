package resources.model;

import java.util.ArrayList;
import java.util.HashMap;

import client.appinterface.Interface;
import resources.model.interfaces.*;

public class MessageHandler {
    private HashMap<Integer, Subscriber> subscribers = new HashMap<>();
    private Interface view;

    public MessageHandler(Interface view){
        this.view = view;
    }
    
    public MessageHandler(Interface view, ArrayList<Subscriber> subs){
        this.view = view;
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
        view.getChatWindow().updateView();
    }
}

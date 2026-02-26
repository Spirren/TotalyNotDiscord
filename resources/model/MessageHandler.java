package resources.model;

import java.util.ArrayList;
import java.util.HashMap;

import client.appinterface.view.ChatWindow;
import resources.model.interfaces.*;

public class MessageHandler {
    private HashMap<Integer, Subscriber> subscribers = new HashMap<>();
    private ChatWindow view;

    public MessageHandler(ChatWindow view){
        this.view = view;
    }
    
    public MessageHandler(ChatWindow view, ArrayList<Subscriber> subs){
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
        view.updateView();
    }
}

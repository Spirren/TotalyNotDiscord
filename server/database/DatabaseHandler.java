package server.database;

import java.util.ArrayList;
import java.util.HashMap;
import resources.model.interfaces.*;

public class DatabaseHandler {
    public HashMap<Integer, ArrayList<Subscriber>> subscribers = new HashMap<>(); // change back to private!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private static DatabaseHandler instance;

    private DatabaseHandler(){

    }
    
    private DatabaseHandler(ArrayList<Subscriber> subs){
        for(Subscriber s : subs){
            if(!subscribers.containsKey(s.getSubKey())){
            subscribers.put(s.getSubKey(), new ArrayList<Subscriber>());
            }
            subscribers.get(s.getSubKey()).add(s);
        }
    }

    public static DatabaseHandler getInstance(){
        if(instance == null){
            instance = new DatabaseHandler();
        }
        return instance;
    }

    public void subscribe(Subscriber s){
        if(!subscribers.containsKey(s.getSubKey())){
            subscribers.put(s.getSubKey(), new ArrayList<Subscriber>());
        }
        subscribers.get(s.getSubKey()).add(s);
        System.out.println("Subscriber " + subscribers.get(s.getSubKey()) + " with subscribers " + subscribers);

        System.out.println(
            "Subscribe : DB instance: " +
            System.identityHashCode(DatabaseHandler.getInstance()) +
            " | ClassLoader: " +
            DatabaseHandler.class.getClassLoader()
        );
    }

    public void unsubscribe(Subscriber s){
        subscribers.get(s.getSubKey()).remove(s);
        if(subscribers.get(s.getSubKey()).isEmpty()){
            subscribers.remove(s.getSubKey());
        }
    }

    public void notifySubscribers(ArrayList<Integer> key, IMessage m){
        System.out.println("The key is... " + key);

        System.out.println(
            "Notify : DB instance: " +
            System.identityHashCode(DatabaseHandler.getInstance()) +
            " | ClassLoader: " +
            DatabaseHandler.class.getClassLoader()
        );

        for(int k : key){
            System.out.println("Current key is " + k);
            System.out.println("For subscriber k " + subscribers.get(k));
            System.out.println("For subscriber 3 " + subscribers.get(3));
            System.out.println("ALL THE SUBSCRIBERS " + subscribers);
            ArrayList<Subscriber> subs = subscribers.getOrDefault(k, new ArrayList<>());
            System.out.println("Updating " + subs + "\n");
            for(Subscriber s : subs){
                s.update(m);
            }
        }
    }
}

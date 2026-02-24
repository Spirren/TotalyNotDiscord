package resources.model;

import resources.model.interfaces.IChat;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

public class Chat implements IChat{
    private LinkedList<IMessage> messages; 
    private LocalDateTime timeCreated;
    private int id;

    public Chat(int id, LocalDateTime timeCreated){
        messages = new LinkedList<>();
        this.timeCreated = timeCreated;
        this.id = id;
    }

    @Override
    public void addMessage(IMessage msg){
        messages.add(msg);
    }

    @Override
    public void addMessages(ArrayList<IMessage> msgs){
        for(IMessage m : msgs){
            this.addMessage(m);
        }
    }

    @Override
    public void removeMessage(IMessage msg){
        messages.remove(msg);
    }

    @Override
    public void editMessage(IMessage oldMsg, IMessage newMsg){ //Should msg be immutable? Should only be able to edit the content
        int index = messages.indexOf(oldMsg);
        messages.remove(index);
        messages.add(index,newMsg);
    }

    @Override
    public Iterator<IMessage> iterator(int index){
        return messages.listIterator(index);
    }

    @Override
    public int getChatId(){
        return this.id;
    }
}
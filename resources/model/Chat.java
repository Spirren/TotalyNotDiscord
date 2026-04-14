package resources.model;

import resources.model.interfaces.IChat;
import resources.model.interfaces.IMessage;
import resources.model.interfaces.Subscriber;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class Chat implements IChat, Subscriber{
    private LinkedList<IMessage> messages; 
    private LocalDateTime timeCreated;
    private int id;

    public Chat(int id, LocalDateTime timeCreated){
        this.messages = new LinkedList<>();
        this.timeCreated = timeCreated;
        this.id = id;
    }

    public Chat(int id, LocalDateTime timeCreated, LinkedList<IMessage> messages){
        this.messages = new LinkedList<>(messages);
        this.timeCreated = timeCreated;
        this.id = id;
    }

    @Override
    public void addMessage(IMessage msg){
        messages.add(msg);
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

    public Iterator<IMessage> iterator(){
        return messages.listIterator();
    }

    @Override
    public int getChatId(){
        return this.id;
    }

    @Override
    public LinkedList<IMessage> getMessages(){
        return this.messages;
    }

    @Override
    public LocalDateTime getTimeCreated(){
        return this.timeCreated;
    }

    @Override
    public String toString(){
        return "Chat{" +
                "id=" + this.id + ", " +
                "timeCreated=" + this.timeCreated + ", " +
                "messages=" + this.messages + "}";
    }

    @Override
    public boolean equals(Object o){
        if(this == o){return true;}
        if(o == null){return false;}
        if(this.getClass() != o.getClass()){return false;}
        return this.messages.equals(((Chat)o).getMessages()) &&
               this.timeCreated.equals(((Chat)o).getTimeCreated()) &&
               this.id == ((Chat)o).getChatId();
    }

    @Override
    public int hashCode(){
        return Objects.hash(messages, timeCreated, id);
    }

    @Override
    public void update(IMessage m){
        this.addMessage(m);
    }

    @Override
    public Integer getSubKey(){
        return this.getChatId();
    }
}
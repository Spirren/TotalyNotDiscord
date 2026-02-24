package resources.model;

import resources.model.interfaces.IChat;
import resources.model.interfaces.IMessage;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

public class Chat implements IChat {
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
<<<<<<< HEAD

    @Override
<<<<<<< HEAD
    public int getChatId(){
        return this.id;
=======
    public Iterator iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
>>>>>>> refs/remotes/origin/main
    }
=======
>>>>>>> refs/remotes/origin/main
}
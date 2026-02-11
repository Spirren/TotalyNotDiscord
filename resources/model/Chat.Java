import java.time.LocalDateTime;
import java.util.LinkedList;

public Class Chat{
    private LinkedList<IMessage> messages; 
    private LocalDateTime timeCreated;
    private int id;

    public Chat(int id, LocalDateTime timeCreated){
        messages = new LinkedList<>();
        this.timeCreated = timeCreated;
        this.id = id;
    }

    @override
    public void addMessage(IMessage msg){
        messages.add(msg);
    }

    @override
    public void removeMessage(IMessage msg){
        messages.remove(msg);
    }

    @override
    public void editMessage(IMessage oldMsg, IMessage newMsg){ //Should msg be immutable? Should only be able to edit the content
        int index = messages.indexOf(oldMsg);
        messages.remove(index);
        messages.add(index,newMsg);
    }

    @override
    public Iterator(int index){
        return messages.listIterator(index);
    }
}
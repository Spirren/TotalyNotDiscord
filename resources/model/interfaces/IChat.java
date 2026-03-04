package resources.model.interfaces;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

public interface IChat extends Iterable, Serializable {
    public void addMessage(IMessage msg);
    public void removeMessage(IMessage msg);
    public void editMessage(IMessage oldMsg, IMessage newMsg);
    public Iterator<IMessage> iterator(int index);
    public int getChatId();
    public LinkedList<IMessage> getMessages();
    public LocalDateTime getTimeCreated();
}

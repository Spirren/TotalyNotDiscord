package resources.model.interfaces;

import java.io.Serializable;
import java.util.Iterator;

public interface IChat extends Iterable, Serializable {
    public void addMessage(IMessage msg);
    public void removeMessage(IMessage msg);
    public void editMessage(IMessage msg, IMessage newMsg);
    public Iterator iterator(int index);
}

package interfaces;
import java.io.Serializable;
import java.util.Iterator;

public interface IChat extends Iterable, Serializable {
    public void addMessage(IMessage msg);
    public void removeMessage(IMessage msg);
    public void editMessage(IMessage oldMsg, IMessage newMsg);
    public Iterator<IMessage> iterator(int index);
    public int getChatId();
}

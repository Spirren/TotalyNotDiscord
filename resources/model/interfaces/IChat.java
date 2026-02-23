package resources.model.interfaces;
<<<<<<< HEAD

import java.io.Serializable;
import java.util.Iterator;

public interface IChat extends Iterable, Serializable {
    public void addMessage(IMessage msg);
    public void removeMessage(IMessage msg);
    public void editMessage(IMessage msg, IMessage newMsg);
    public Iterator iterator(int index);
=======
import java.util.Iterator;

public interface IChat extends Iterable {
    public void addMessage(IMessage msg);
    public void removeMessage(IMessage msg);
    public void editMessage(IMessage oldMsg, IMessage newMsg);
    public Iterator<IMessage> iterator(int index);
>>>>>>> 182fe71692be1eb695b0887188d78341ce16d635
}

package resources.model.interfaces;

import resources.model.Message;

public interface IChat extends Iterable {
    public void addMessage(Message msg);
    public void removeMessage(Message msg);
    public void editMessage(Message msg);
}

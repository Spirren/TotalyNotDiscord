package resources.model.interfaces;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IMessage<T> extends Serializable{
    public LocalDateTime getTime();
    public LocalDateTime getLastEdited();
    public IUser getSender();
    public T getContent();
    public int getIndex();
    public int getChatID();
}

package resources.model.interfaces;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IMessage extends Serializable{
    public LocalDateTime getTime();
    public LocalDateTime getLastEdited();
    public IUser getSender();
    public String getContent();
    public int getIndex();
    public int getChatID();
}

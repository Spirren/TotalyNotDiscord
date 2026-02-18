package resources.model.interfaces;

import java.time.LocalDateTime;

public interface IMessage {
    public LocalDateTime getTime();
    public LocalDateTime getLastEdited();
    public IUser getSender();
    public String getContent();
    public int getIndex();
}

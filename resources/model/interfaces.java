import java.util.LinkedList;
import java.time.LocalDateTime;
import java.time.LocalDate;

interface IChat extends Iterable{
    public void addMessage(Message msg);
    public void removeMessage(Message msg);
    public void editMessage(Message msg);
}

interface IMessage {
    public LocalDateTime getTime();
    public LocalDateTime getLastEdited();
    public IUser getSender();
    public String getContent();
}

interface IUser{
    public String getName();
    public String getEmail();
    public LocalDate getBirthYear();
    public int getID();
}
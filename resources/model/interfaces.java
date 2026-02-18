import java.util.LinkedList;
import java.time.LocalDateTime;
import java.time.LocalDate;

interface IChat extends Iterable{
    public void addMessage(IMessage msg);
    public void removeMessage(IMessage msg);
    public void editMessage(IMessage msg);
    public int getChatId();
}

interface IMessage {
    public LocalDateTime getTime();
    public LocalDateTime getLastEdited();
    public IUser getSender();
    public String getContent();
    public int getIndex();
}

interface IUser{
    public String getName();
    public String getEmail();
    public LocalDate getBirthYear();
    public int getID();
    public int getPassword();
}

interface Subscriber {
    public void update(IMessage m);
    public <T> T getSubKey();
}
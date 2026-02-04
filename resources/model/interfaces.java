import java.util.LinkedList;

interface iChat {
    public void addMessage(Message msg);
    public void deleteMessage(Message msg);
    public void editMessage(Message msg);
}

interface iMessage {
    public void editMessage(Message msg);
}
package resources.model.Services;

import resources.model.Message;

public class MessageService {
    public void add(Message m) { System.out.println("Add Message " + m.getIndex()); }
    public void delete(Message m) { System.out.println("Delete Message " + m.getIndex()); }
    public void modify(Message m) { System.out.println("Modify Message " + m.getIndex()); }
}

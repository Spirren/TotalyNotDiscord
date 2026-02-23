package resources.model.ServerServices;

import resources.model.interfaces.IMessage;

public class MessageService {
    public void add(IMessage m) { System.out.println("Add Message " + m.getIndex()); }
    public void delete(IMessage m) { System.out.println("Delete Message " + m.getIndex()); }
    public void modify(IMessage m) { System.out.println("Modify Message " + m.getIndex()); }
}

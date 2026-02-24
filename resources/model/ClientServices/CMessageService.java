package resources.model.ClientServices;

import resources.model.MessageHandler;
import resources.model.interfaces.IMessage;

public class CMessageService {
    private MessageHandler msgHandler;

    public CMessageService(MessageHandler msgHandler) {
        this.msgHandler = msgHandler;
    }

    public void add(IMessage m) {
        System.out.println("Add Message " + m.getIndex());
        msgHandler.notifySubscribers(m.getChatID(), m);
    }
    public void delete(IMessage m) { System.out.println("Delete Message " + m.getIndex()); }
    public void modify(IMessage m) { System.out.println("Modify Message " + m.getIndex()); }
}

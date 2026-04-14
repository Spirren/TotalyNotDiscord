package client.ClientServices;

import client.observer.MessageHandler;
import resources.model.interfaces.IMessage;

public class CMessageService {
    private MessageHandler msgHandler;

    public CMessageService(MessageHandler msgHandler) {
        this.msgHandler = msgHandler;
    }

    public void addMessage(IMessage m) {
        System.out.println("Add Message " + m.getIndex());
        msgHandler.notifySubscribers(m.getChatID(), m);
    }
}

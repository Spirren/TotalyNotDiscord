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

    public void deleteMessage(IMessage m) {
        System.out.println("Delete Message " + m.getIndex());
        /*
         * try {
         * SqlUtils.deleteMessage(new PostgresConnectionProvider().getConnection(), ,
         * m.getChatID());
         * } catch (SQLException e) {
         * System.out.println("Error communicating with database");
         * e.printStackTrace();
         * }
         */
    }

    public void modifyMessage(IMessage m) {
        System.out.println("Modify Message " + m.getIndex());
        /*
         * try {
         * SqlUtils.modifyMessage(new PostgresConnectionProvider().getConnection(), ,
         * m.getChatID());
         * } catch (SQLException e) {
         * System.out.println("Error communicating with database");
         * e.printStackTrace();
         * }
         */
    }
}

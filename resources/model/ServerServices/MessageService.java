package resources.model.ServerServices;

import java.sql.SQLException;

import resources.model.Message;
import resources.model.interfaces.IMessage;
import resources.sockets.ClientHandler;
import server.database.PostgresConnectionProvider;
import server.database.SqlUtils;

public class MessageService {
    private final ClientHandler handler;

    public MessageService(ClientHandler handler) {
        this.handler = handler;
    }

    public void add(IMessage m) {
        System.out.println("Add Message " + m.getIndex()); 
        try {
            SqlUtils.addMessage(new PostgresConnectionProvider().getConnection(), (Message) m, m.getChatID());
        } catch (SQLException e) {
            System.out.println("Error communicating with database");
            e.printStackTrace();
        }
    }
    public void delete(IMessage m) {
        System.out.println("Delete Message " + m.getIndex());
        /*try {
            SqlUtils.deleteMessage(new PostgresConnectionProvider().getConnection(), (Message) m, m.getChatID());
        } catch (SQLException e) {
            System.out.println("Error communicating with database");
            e.printStackTrace();
        }*/
    }
    public void modify(IMessage m) {
        System.out.println("Modify Message " + m.getIndex());
        /*try {
            SqlUtils.modifyMessage(new PostgresConnectionProvider().getConnection(), (Message) m, m.getChatID());
        } catch (SQLException e) {
            System.out.println("Error communicating with database");
            e.printStackTrace();
        }*/
    }
}

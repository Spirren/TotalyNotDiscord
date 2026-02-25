package resources.model.ServerServices;

import java.io.IOException;
import java.sql.SQLException;

import resources.model.Image;
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

    public void addMessage(Message m) {
        System.out.println("Add Message " + m.getIndex()); 
        try {
            SqlUtils.addMessage(new PostgresConnectionProvider().getConnection(), m, m.getChatID());
        } catch (SQLException e) {
            System.out.println("Error communicating with database");
            e.printStackTrace();
        }
    }
    public void deleteMessage(Message m) {
        System.out.println("Delete Message " + m.getIndex());
        /*try {
            SqlUtils.deleteMessage(new PostgresConnectionProvider().getConnection(), , m.getChatID());
        } catch (SQLException e) {
            System.out.println("Error communicating with database");
            e.printStackTrace();
        }*/
    }
    public void modifyMessage(Message m) {
        System.out.println("Modify Message " + m.getIndex());
        /*try {
            SqlUtils.modifyMessage(new PostgresConnectionProvider().getConnection(), , m.getChatID());
        } catch (SQLException e) {
            System.out.println("Error communicating with database");
            e.printStackTrace();
        }*/
    }

    public void addImage(Image im) {
        System.out.println("Add Message " + im.getIndex()); 
        try {
            SqlUtils.addMessage(new PostgresConnectionProvider().getConnection(), m, m.getChatID());
        } catch (SQLException e) {
            System.out.println("Error communicating with database");
            e.printStackTrace();
        }
    }
    public void deleteImage(Image im) {
        System.out.println("Delete Message " + im.getIndex());
        /*try {
            SqlUtils.deleteMessage(new PostgresConnectionProvider().getConnection(), m, m.getChatID());
        } catch (SQLException e) {
            System.out.println("Error communicating with database");
            e.printStackTrace();
        }*/
    }
    public void modifyImage(Image im) {
        System.out.println("Modify Message " + im.getIndex());
        /*try {
            SqlUtils.modifyMessage(new PostgresConnectionProvider().getConnection(), m, m.getChatID());
        } catch (SQLException e) {
            System.out.println("Error communicating with database");
            e.printStackTrace();
        }*/
    }
}
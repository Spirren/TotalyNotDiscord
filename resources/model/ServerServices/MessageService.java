package resources.model.ServerServices;

import java.io.IOException;
import java.sql.SQLException;
import resources.model.interfaces.IImageMessage;
import resources.model.interfaces.ITextMessage;
import server.database.DatabaseOperator;

public class MessageService {

    public void addMessage(ITextMessage m) {
        System.out.println("Add Message " + m.getIndex() + " with payload " + m.getContent());
        try {
            DatabaseOperator.getInstance().addMessageContent(m, m.getChatID());
        } catch (SQLException e) {
            System.out.println("Error communicating with database");
            e.printStackTrace();
        }
    }

    public void deleteMessage(ITextMessage m) {
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

    public void modifyMessage(ITextMessage m) {
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

    public void addImage(IImageMessage im) {
        System.out.println("Add Message " + im.getIndex());
        try {
            DatabaseOperator.getInstance().addMessageContent(im, im.getChatID());
        } catch (SQLException | IOException e) {
            System.out.println("Error communicating with database");
            e.printStackTrace();
        }
    }

    public void deleteImage(IImageMessage im) {
        System.out.println("Delete Message " + im.getIndex());
        /*
         * try {
         * SqlUtils.deleteMessage(new PostgresConnectionProvider().getConnection(), m,
         * m.getChatID());
         * } catch (SQLException e) {
         * System.out.println("Error communicating with database");
         * e.printStackTrace();
         * }
         */
    }

    public void modifyImage(IImageMessage im) {
        System.out.println("Modify Message " + im.getIndex());
        /*
         * try {
         * SqlUtils.modifyMessage(new PostgresConnectionProvider().getConnection(), m,
         * m.getChatID());
         * } catch (SQLException e) {
         * System.out.println("Error communicating with database");
         * e.printStackTrace();
         * }
         */
    }
}
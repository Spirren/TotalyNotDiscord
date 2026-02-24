package resources.model.ServerServices;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import resources.model.LoginRequest;
import resources.model.dispatcher.DispatchRequest;
import resources.model.interfaces.IChat;
import resources.model.interfaces.IUser;
import resources.model.types.OperationType;
import resources.sockets.ClientHandler;
import server.database.PostgresConnectionProvider;
import server.database.SqlUtils;

public class LoginService {
    public void login(LoginRequest lr, ClientHandler handler) {
        System.out.println("Login user " + lr.getUsername() + " with password " + lr.getPassword());
        try {
            IUser user = SqlUtils.getUser(new PostgresConnectionProvider().getConnection(), lr.getUsername());
            if (user != null) {
                handler.setUser(user);
                ArrayList<IChat> chats = SqlUtils.getUserChats(new PostgresConnectionProvider().getConnection(), user.getID());
                for (IChat chat : chats) {
                    user.addChat(chat);
                }
                handler.send(new DispatchRequest(user, OperationType.LOGIN));
            } else {
                handler.send(new DispatchRequest(lr, OperationType.ERROR));
            }
        } catch (SQLException e) {
            System.out.println("Error communicating with database");
            e.printStackTrace();
        }
    }
}

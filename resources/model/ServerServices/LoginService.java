package resources.model.ServerServices;

import java.sql.SQLException;
import java.util.ArrayList;

import resources.model.LoginRequest;
import resources.model.dispatcher.DispatchRequest;
import resources.model.interfaces.IChat;
import resources.model.interfaces.IUser;
import resources.model.types.OperationType;
import resources.sockets.ClientHandler;
import server.database.DatabaseHandler;
import server.database.DatabaseOperator;

public class LoginService {
    public void login(LoginRequest lr, ClientHandler handler) {
        System.out.println("Logging in user " + lr.getUsername() + " with password " + lr.getPassword());
        try {
            DatabaseOperator DBoperator = DatabaseOperator.getInstance();
            IUser user = DBoperator.getUser(lr.getUsername());
            if (user != null) {
                handler.setUser(user);
                DatabaseHandler.getInstance().subscribe(handler);
                System.out.println("For subscriber login " + DatabaseHandler.getInstance().subscribers.get(3));
                ArrayList<IChat> chats = DBoperator.getUserChats(user.getID());
                for (IChat chat : chats) {
                    user.addChat(chat);
                }
                handler.send(new DispatchRequest(user, OperationType.LOGIN));
            } else {
                System.out.println("User not found");
                handler.send(new DispatchRequest(lr, OperationType.ERROR));
            }
        } catch (SQLException e) {
            System.out.println("Error communicating with database");
            e.printStackTrace();
        }
    }
}

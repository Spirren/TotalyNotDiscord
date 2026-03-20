package resources.model.ServerServices;

import java.sql.SQLException;
import java.util.ArrayList;

import resources.model.LoginRequestGranted;
import resources.model.dispatcher.DispatchRequest;
import resources.model.interfaces.IChat;
import resources.model.interfaces.ILoginRequest;
import resources.model.interfaces.IUser;
import resources.model.types.OperationType;
import resources.sockets.ClientHandler;
import server.database.DatabaseHandler;
import server.database.DatabaseOperator;

public class LoginService {
    private ClientHandler handler;

    public LoginService(ClientHandler handler) {
        this.handler = handler;
    }

    public void login(ILoginRequest lr) {
        System.out.println("Logging in user " + lr.getUsername() + " with password " + lr.getPassword());
        try {
            DatabaseOperator DBoperator = DatabaseOperator.getInstance();
            IUser user = DBoperator.getUser(lr.getUsername());
            if (user != null) {
                handler.setUser(user);
                DatabaseHandler.getInstance().subscribe(handler);
                System.out.println("For subscriber login " + DatabaseHandler.getInstance().subscribers.get(3));
                ArrayList<IChat> chats = DBoperator.getUserChats(user.getID());
                handler.send(new DispatchRequest(new LoginRequestGranted(user, chats), OperationType.LOGIN));
            } else {
                System.out.println("User not found");
                handler.send(new DispatchRequest(lr, OperationType.ERROR));
            }
        } catch (SQLException e) {
            System.out.println("Error communicating with database");
            e.printStackTrace();
        }
    }
    public void loginFail(ILoginRequest lr) { System.out.println("Logging in user " + lr.getUsername()); }
}

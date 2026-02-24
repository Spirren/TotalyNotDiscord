package resources.model.ServerServices;

import java.sql.SQLException;

import resources.model.LoginRequest;
import resources.model.User;
import resources.model.dispatcher.DispatchRequest;
import resources.model.types.OperationType;
import resources.sockets.ClientHandler;
import server.database.PostgresConnectionProvider;
import server.database.SqlUtils;

public class LoginService {
    public void login(LoginRequest lr, ClientHandler handler) {
        System.out.println("Login user " + lr.getUsername() + " with password " + lr.getPassword());
        try {
            User user = SqlUtils.getUser(new PostgresConnectionProvider().getConnection(), lr.getUsername());
            if (user.getPassword() == lr.getPassword() && user != null) {
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

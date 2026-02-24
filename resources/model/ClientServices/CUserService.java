package resources.model.ClientServices;

import resources.model.interfaces.IUser;
import resources.sockets.ChatClient;

public class CUserService {
    private final ChatClient client;

    public CUserService(ChatClient client) {
        this.client = client;
    }
    public void add(IUser u) { System.out.println("Add User " + u.getID()); }
    public void delete(IUser u) { System.out.println("Delete User " + u.getID()); }
    public void modify(IUser u) { System.out.println("Modify User " + u.getID()); }
    public void login(IUser u) {
        System.out.println("Login user " + u.getID());
        client.setUser(u);
    }
}

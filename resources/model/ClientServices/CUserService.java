package resources.model.ClientServices;



import java.util.Iterator;

import resources.model.ChatListener;
import resources.model.MessageHandler;
import resources.model.interfaces.IChat;
import resources.model.interfaces.IUser;
import resources.sockets.ChatClient;

public class CUserService {
    private final ChatClient client;
    private MessageHandler msgHandler;

    public CUserService(ChatClient client, MessageHandler msgHandler) {
        this.client = client;
        this.msgHandler = msgHandler;
    }
    public void add(IUser u) { System.out.println("Add User " + u.getID()); }
    public void delete(IUser u) { System.out.println("Delete User " + u.getID()); }
    public void modify(IUser u) { System.out.println("Modify User " + u.getID()); }
    public void login(IUser u) {
        System.out.println("Login user " + u.getName());
        client.setUser(u);
        for(IChat chat : u){
            msgHandler.subscribe(new ChatListener(chat));
        }
    }
}

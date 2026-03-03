package resources.model.ClientServices;

import client.appinterface.Interface;
import resources.model.ChatListener;
import resources.model.MessageHandler;
import resources.model.interfaces.IChat;
import resources.model.interfaces.ILoginRequestGranted;
import resources.model.interfaces.ISidebarUpdateListener;
import resources.sockets.ChatClient;

public class CLoginService {
    private final ChatClient client;
    private MessageHandler msgHandler;
    private ISidebarUpdateListener ui;

    public CLoginService(ChatClient client, MessageHandler msgHandler, ISidebarUpdateListener ui) {
        this.client = client;
        this.msgHandler = msgHandler;
        this.ui = ui;
    }

    public void login(ILoginRequestGranted lr) {
        System.out.println("Login user " + lr.getUser().getName());
        if (!client.isLoggedIn()) {
            client.setUser(lr.getUser());
        for(IChat chat : lr.getChats()){
            msgHandler.subscribe(new ChatListener(chat));
        }
        ui.updateSideBar(lr.getChats());
        }
    }
    public void loginFail(ILoginRequestGranted lr) { System.out.println("Could not login user " + lr.getUser().getName()); }
}

package resources.model;

import java.util.ArrayList;
import resources.model.interfaces.IChat;
import resources.model.interfaces.IUser;
import resources.model.interfaces.ILoginRequestGranted;

public class LoginRequestGranted implements ILoginRequestGranted {
    private final IUser user;
    private ArrayList<IChat> chats;

    public LoginRequestGranted(IUser user, ArrayList<IChat> chats) {
        this.user = user;
        this.chats = chats;
    }

    @Override
    public IUser getUser() {
        return user;
    }

    @Override
    public ArrayList<IChat> getChats() {
        return chats;
    }
}

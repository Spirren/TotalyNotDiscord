package resources.model.interfaces;

import java.io.Serializable;
import java.util.ArrayList;

public interface ILoginRequestGranted extends Serializable{    
    public ArrayList<IChat> getChats();
    public IUser getUser();
}

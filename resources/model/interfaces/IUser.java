package resources.model.interfaces;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Iterator;

public interface IUser extends Serializable {
    public String getName();

    public String getEmail();

    public LocalDate getBirthYear();

    public int getID();

    public void addChat(IChat chat);

    public Iterator<IChat> iterator(int index);
}

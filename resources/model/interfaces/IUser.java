package resources.model.interfaces;

import java.io.Serializable;
import java.time.LocalDate;

public interface IUser extends Serializable {
    public String getName();

    public String getEmail();

    public LocalDate getBirthYear();

    public int getID();
}

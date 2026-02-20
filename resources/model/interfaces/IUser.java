package resources.model.interfaces;

import java.time.LocalDate;

public interface IUser {
    public String getName();

    public String getEmail();

    public LocalDate getBirthYear();

    public int getID();

    public int getPassword();
}

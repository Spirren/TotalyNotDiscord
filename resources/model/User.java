package resources.model;
import java.time.LocalDate;
import java.util.Objects;

import resources.model.interfaces.*;

public class User implements IUser {
    private String userName;
    private String email;
    private final LocalDate birthYear;
    private final int id; //map user to chats

    public User(String userName, String email, LocalDate birthYear, int id){
        this.userName = userName;
        this.email = email;
        this.birthYear = birthYear;
        this.id = id;
    }

    @Override
    public String getName(){
        return userName;
    }

    @Override
    public String getEmail(){
        return email;
    }

    @Override
    public LocalDate getBirthYear(){
        return birthYear;
    }

    @Override
    public int getID(){
        return id;
    }

    @Override
    public String toString(){
        return "User{" +
                "id=" + this.id + ", " +
                "birthYear=" + this.birthYear + ", " +
                "email=" + this.email + ", " +
                "userName=" + this.userName + ", " +
                "}";
    }

    @Override
    public boolean equals(Object o){
        if(this == o){return true;}
        if(o == null){return false;}
        if(this.getClass() != o.getClass()){return false;}
        return this.userName.equals(((User)o).getName()) &&
               this.email.equals(((User)o).getEmail()) &&
               this.birthYear.equals(((User)o).getBirthYear()) &&
               this.id == ((User)o).getID();
    }

    @Override
    public int hashCode(){
        return Objects.hash(userName, email, birthYear, id);
    }
}
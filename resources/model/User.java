package resources.model;
import java.time.LocalDate;
import resources.model.interfaces.IUser;

public class User implements IUser {
    private String userName;
    private String email;
    private LocalDate birthYear;
    private int id; //map user to chats
    private String password;

<<<<<<< HEAD
    public User(String userName, String email, LocalDate birthYear, int id, String password){
=======
    public User(String userName, String email, LocalDate birthYear, int id, int password){
>>>>>>> 182fe71692be1eb695b0887188d78341ce16d635
        this.userName = userName;
        this.email = email;
        this.birthYear = birthYear;
        this.id = id;
        this.password = password;
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
    public String getPassword(){
        return password;
    }
}
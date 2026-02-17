import java.time.LocalDate;

public class Users implements IUser {
    private String userName;
    private String email;
    private LocalDate birthYear;
    private int id; //map user to chats
    private int password;

    public Users(String userName, String email, LocalDate birthYear, int id, int password){
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
    public int getPassword(){
        return password;
    }
}
import java.time.LocalDate;

public class Users{
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

    @override
    public String getName(){
        return userName;
    }

    @override
    public String getEmail(){
        return email;
    }

    @override
    public LocalDate getBirthYear(){
        return birthYear;
    }

    @override
    public int getID(){
        return id;
    }

    @override
    public int getPassword(){
        return password;
    }
}
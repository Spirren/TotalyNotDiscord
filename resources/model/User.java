package resources.model;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import resources.model.interfaces.*;

public class User implements IUser {
    private String userName;
    private String email;
    private LocalDate birthYear;
    private int id; //map user to chats
    private LinkedList<IChat> chats = new LinkedList<>();

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
    public void addChat(IChat c){
        chats.add(c);
    }

    @Override
    public Iterator<IChat> iterator(int index){
        return chats.listIterator(index);
    }

    public Iterator<IChat> iterator(){
        return chats.listIterator();
    }
}
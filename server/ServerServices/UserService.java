package server.ServerServices;

import resources.model.interfaces.IUser;

public class UserService {
    public void add(IUser u) { System.out.println("Add User " + u.getID()); }
    public void delete(IUser u) { System.out.println("Delete User " + u.getID()); }
    public void modify(IUser u) { System.out.println("Modify User " + u.getID()); }
    public void login(IUser u) { System.out.println("Login user " + u.getID()); }
}

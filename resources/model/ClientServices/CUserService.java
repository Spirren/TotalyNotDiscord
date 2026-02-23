package resources.model.ClientServices;

import resources.model.User;

public class CUserService {
    public void add(User u) { System.out.println("Add User " + u.getID()); }
    public void delete(User u) { System.out.println("Delete User " + u.getID()); }
    public void modify(User u) { System.out.println("Modify User " + u.getID()); }
    public void login(User u) { System.out.println("Login user " + u.getID()); }
}

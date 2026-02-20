package resources.model.Services;

import resources.model.User;

public class UserService {
    public void add(User u) { System.out.println("Add User " + u.getID()); }
    public void delete(User u) { System.out.println("Delete User " + u.getID()); }
    public void modify(User u) { System.out.println("Modify User " + u.getID()); }
}

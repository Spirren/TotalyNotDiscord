package resources.model.ServerServices;

import resources.model.LoginRequest;

public class LoginService {
    public void login(LoginRequest lr) { System.out.println("Login user " + lr.getUsername() + " with password " + lr.getPassword()); }
}

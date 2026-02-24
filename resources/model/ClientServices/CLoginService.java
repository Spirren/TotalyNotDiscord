package resources.model.ClientServices;

import resources.model.LoginRequest;

public class CLoginService {
    public void login(LoginRequest lr) { System.out.println("Login user " + lr.getUsername()); }
    public void loginFail(LoginRequest lr) { System.out.println("Could not login user " + lr.getUsername()); }
}

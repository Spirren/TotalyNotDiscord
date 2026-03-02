package resources.model;

import resources.model.interfaces.ILoginRequest;

public class LoginRequest implements ILoginRequest {
    private final String username;
    private final String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

package resources.model.interfaces;

import java.io.Serializable;

public interface ILoginRequest extends Serializable {
    public String getUsername();
    public String getPassword();
}

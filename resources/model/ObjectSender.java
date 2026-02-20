package resources.model;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectSender {
    private ObjectOutputStream out;

    public ObjectSender(ObjectOutputStream out) {
        this.out = out;
    }

    public void send(Object cmd) {
        try {
            out.reset();
            out.writeObject(cmd);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package resources.model;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;

import resources.model.interfaces.ObjectHandler;

public class ObjectReceiver {
    private final ObjectInputStream in;
    private final ObjectHandler handler;

    public ObjectReceiver(ObjectInputStream in, ObjectHandler handler) {
        this.in = in;
        this.handler = handler;
    }
    
    public void listen() {
        try {
            while (true) {
                Object obj = in.readObject();
                handler.handle(obj);
            }
        } catch (EOFException | SocketException e) {
            System.out.println("Connection closed");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
package resources.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;

import resources.model.dispatcher.DispatchRequest;
import resources.model.interfaces.ObjectHandler;

public class ObjectReceiver {
    private final ObjectInputStream in;
    private final ObjectHandler<DispatchRequest> handler;

    public ObjectReceiver(ObjectInputStream in, ObjectHandler<DispatchRequest> handler) {
        this.in = in;
        this.handler = handler;
    }
    
    public void listen() {
        try {
            while (true) {
                DispatchRequest req = (DispatchRequest) in.readObject();
                handler.handle(req);
            }
        } catch (SocketException e) {
            System.out.println("Connection lost");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
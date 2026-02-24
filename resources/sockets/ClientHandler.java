package resources.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import resources.model.interfaces.*;
import resources.model.ObjectReceiver;
import resources.model.ObjectSender;
import resources.model.ServerServices.DispatchContext;
import resources.model.dispatcher.DispatchObjectHandler;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final ObjectSender sender;
    private final ObjectReceiver receiver;
    private IUser user;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;

        DispatchContext context = new DispatchContext(this);
        ObjectHandler handler = new DispatchObjectHandler(context.getDispatcher());

        this.sender = new ObjectSender(new ObjectOutputStream(socket.getOutputStream()));
        this.receiver = new ObjectReceiver(new ObjectInputStream(socket.getInputStream()), handler);
    }

    public void setUser(IUser user) {
        this.user = user;
    }

    public IUser getUser() {
        return user;
    }

    @Override
    public void run() {
        receiver.listen();
    }

    public void send(Object response) {
        sender.send(response);
    }
}

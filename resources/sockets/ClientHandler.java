package resources.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import resources.model.interfaces.*;
import resources.model.*;
import resources.model.dispatcher.DispatchObjectHandler;
import resources.model.dispatcher.Dispatcher;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final ObjectSender sender;
    private final ObjectReceiver receiver;

    public ClientHandler(Socket socket, Dispatcher dispatcher) throws IOException {
        this.socket = socket;

        ObjectHandler handler = new DispatchObjectHandler(dispatcher);

        this.sender = new ObjectSender(new ObjectOutputStream(socket.getOutputStream()));
        this.receiver = new ObjectReceiver(new ObjectInputStream(socket.getInputStream()), handler);
    }

    @Override
    public void run() {
        receiver.listen();
    }

    public void send(Object response) {
        sender.send(response);
    }
}

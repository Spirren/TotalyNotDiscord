package client.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import resources.model.LoginRequest;
import client.observer.MessageHandler;
import resources.model.ObjectReceiver;
import resources.model.ObjectSender;
import resources.model.dispatcher.DispatchObjectHandler;
import resources.model.dispatcher.DispatchRequest;
import resources.model.dispatcher.Dispatcher;
import resources.model.interfaces.IChat;
import resources.model.interfaces.IUser;
import resources.model.interfaces.ObjectHandler;
import resources.model.types.OperationType;

public class ChatClient extends Thread { // INTERFACE
    private Socket socket;
    private String host;
    private int port;
    private ObjectSender sender;
    private ObjectReceiver receiver;
    private IUser user;
    private LinkedList<IChat> chats;
    private final Dispatcher dispatcher;
    private Boolean loggedIn = false;

    public void setUser(IUser user) {
        this.user = user;
        this.loggedIn = true;
    }

    public IUser getUser() {
        return user;
    }

    public void setChats(LinkedList<IChat> chats) {
        this.chats = chats;
    }

    public LinkedList<IChat> getChats() {
        return chats;
    }

    public Boolean isLoggedIn() {
        return loggedIn;
    }

    public ChatClient(String host, int port, MessageHandler msgHandler, Dispatcher dispatcher) throws IOException{
        this.dispatcher = dispatcher;
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ObjectHandler<DispatchRequest> handler = new DispatchObjectHandler(this.dispatcher);
                socket = new Socket(host, port);
                this.sender = new ObjectSender(new ObjectOutputStream(socket.getOutputStream()));
                this.receiver = new ObjectReceiver(new ObjectInputStream(socket.getInputStream()), handler);
                System.out.println("Connected to server");
                if (loggedIn) {
                    send(new DispatchRequest(new LoginRequest(user.getName(), "password"), OperationType.LOGIN));
                }
                receiver.listen();
            } catch (IOException e) {
                System.out.println("Connection to server could not be made.");
                try {
                    sleep(5000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public void send(Object msg) {
        sender.send(msg);
    }
}

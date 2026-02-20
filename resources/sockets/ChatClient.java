package resources.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

import resources.model.Message;
import resources.model.ObjectReceiver;
import resources.model.ObjectSender;
import resources.model.User;
import resources.model.dispatcher.DispatchObjectHandler;
import resources.model.dispatcher.DispatchRequest;
import resources.model.dispatcher.Dispatcher;
import resources.model.interfaces.IUser;
import resources.model.interfaces.ObjectHandler;
import resources.model.types.OperationType;

public class ChatClient extends Thread {
    private Socket socket;
    private ObjectSender sender;
    private ObjectReceiver receiver;
    private IUser user;

    public ChatClient(String host, int port, IUser user, Dispatcher dispatcher) throws IOException {
        socket = new Socket(host, port);

        ObjectHandler handler = new DispatchObjectHandler(dispatcher);

        this.sender = new ObjectSender(new ObjectOutputStream(socket.getOutputStream()));
        this.receiver = new ObjectReceiver(new ObjectInputStream(socket.getInputStream()), handler);
        this.user = user;
        
        System.out.println("Connected to server");
    }
    // FOR TESTING
     public ChatClient(String host, int port, Dispatcher dispatcher) throws IOException {
        this(host, port, null, dispatcher);
    }

    @Override
    public void run() {
        receiver.listen();
    }

    public void send(Object command) {
        sender.send(command);
    }

    // For testing purposes
    public static void main(String[] args) throws IOException{
        Dispatcher dispatcher = new Dispatcher();
        ChatClient client = new ChatClient("localhost", 5000, dispatcher);
        client.start();

        
        Scanner scanner = new Scanner(System.in);

        User test = new User("John", "mail@mail.com", null, 1, 1234);
        int messageIndex = 0;

        while (true) {
            System.out.print("Enter message: ");
            String input = scanner.nextLine();

            Message msg = new Message(LocalDateTime.now(), input, test, messageIndex);
            messageIndex++;
            client.send(new DispatchRequest(msg, OperationType.ADD));
        }
        // For testing purposes
    }
}

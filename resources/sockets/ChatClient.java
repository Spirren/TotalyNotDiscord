package resources.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;
import resources.model.ClientServices.CDispatchContext;
import resources.model.LoginRequest;
import resources.model.Message;
import resources.model.MessageHandler;
import resources.model.ObjectReceiver;
import resources.model.ObjectSender;
import resources.model.dispatcher.DispatchObjectHandler;
import resources.model.dispatcher.DispatchRequest;
import resources.model.interfaces.IMessage;
import resources.model.interfaces.IUser;
import resources.model.interfaces.ObjectHandler;
import resources.model.types.OperationType;

public class ChatClient extends Thread {
    private Socket socket;
    private ObjectSender sender;
    private ObjectReceiver receiver;
    private IUser user;
    private MessageHandler msgHandler;

    public void setUser(IUser user) {
        this.user = user;
    }

    public IUser getUser() {
        return user;
    }

    public ChatClient(String host, int port) throws IOException {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            System.out.println("Connection to server could not be made.");
        }

        MessageHandler msgHandler = new MessageHandler(null);
        CDispatchContext context = new CDispatchContext(this, msgHandler);
        ObjectHandler handler = new DispatchObjectHandler(context.getDispatcher());

        this.sender = new ObjectSender(new ObjectOutputStream(socket.getOutputStream()));
        this.receiver = new ObjectReceiver(new ObjectInputStream(socket.getInputStream()), handler);
        
        System.out.println("Connected to server");
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
        ChatClient client = new ChatClient("localhost", 5000);
        client.start();

        
        Scanner scanner = new Scanner(System.in);

        while (client.getUser() == null) {
            System.out.print("Login: ");
            String input = scanner.nextLine();

            LoginRequest login = new LoginRequest(input, "12345");

            if (input != "") client.send(new DispatchRequest(login, OperationType.LOGIN));
        }

        while (true) {
            System.out.print("Enter message: ");
            String input = scanner.nextLine();

            // Message msg = new Message(LocalDateTime.now(), input, test, messageIndex);

            IMessage<String> msg = new Message(LocalDateTime.now() , input, client.getUser(), 0);

            client.send(new DispatchRequest(msg, OperationType.ADD));
        }
        // For testing purposes
    }
}

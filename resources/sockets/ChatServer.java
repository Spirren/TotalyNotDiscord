package resources.sockets;

import java.io.*;
import java.net.*;

import resources.model.DispatchContext;

public class ChatServer {
    public ServerSocket serverSocket;

    public ChatServer(int port) throws IOException{
        serverSocket = new ServerSocket(port);
        System.out.println("Server listening on port: " + port);
    }

    /** Starts listening on {@CODE port} set by contructor
    */
    public void start() {
        try {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected on: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

            DispatchContext context = new DispatchContext();
            ClientHandler handler = new ClientHandler(clientSocket, context.dispatcher);
            handler.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

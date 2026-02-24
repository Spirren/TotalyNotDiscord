package resources.sockets;

import java.io.*;
import java.net.*;

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

            ClientHandler handler = new ClientHandler(clientSocket);
            handler.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // java -cp ".;server/database/pgjdbc-ng-all-0.8.9.jar" .\resources\sockets\ChatServer.java
        try {
            ChatServer server = new ChatServer(5000);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

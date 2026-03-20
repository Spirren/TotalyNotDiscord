package resources.sockets;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.SQLException;

import server.database.MessageDataReciver;
import server.database.PostGressStreamSubscriber;
import server.database.PostgresConnectionProvider;
import server.database.SqlUtils;
import server.database.StreamListener;
import server.database.DatabaseOperator;

public class ChatServer {
    public ServerSocket serverSocket;

    public ChatServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server listening on port: " + port);
    }

    /**
     * Starts listening on {@CODE port} set by contructor
     */
    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println(
                        "Client connected on: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

                new ClientHandler(clientSocket).start();
            } catch (IOException e) {
                e.printStackTrace();
                // DatabaseHandler.getInstance().unsubscribe(handler);

            }
        }
    }

    public static void main(String[] args) {
        // java -cp ".;server/database/pgjdbc-ng-all-0.8.9.jar"
        // .\resources\sockets\ChatServer.java
        try {

            Connection conn = new PostgresConnectionProvider().getConnection();
            DatabaseOperator.setInstance(new SqlUtils(conn));

            StreamListener listener = new StreamListener(
                    new MessageDataReciver(),
                    new PostGressStreamSubscriber(new PostgresConnectionProvider()),
                    "new_message");

            Thread thread = new Thread(listener); // Runnable passed to thread
            thread.start(); // runs asynchronously
            ChatServer server = new ChatServer(5000);
            server.start();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}

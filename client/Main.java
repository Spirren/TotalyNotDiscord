package client;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.swing.SwingUtilities;

import client.appinterface.Interface;
import client.observer.MessageHandler;
import client.ClientServices.CDispatchContext;
import resources.model.dispatcher.Dispatcher;
import client.sockets.ChatClient;

public class Main {

    public static void main(String[] args) {

        Interface ui = new Interface();

        MessageHandler messageHandler = new MessageHandler(ui);
        try {
            Dispatcher dispatcher = new Dispatcher();
            ChatClient client = new ChatClient("localhost", 5000, messageHandler, dispatcher);
            CDispatchContext context = new CDispatchContext(client, messageHandler, ui, dispatcher);
            client.start();

            // Always start Swing UI on Event Dispatch Thread
            SwingUtilities.invokeLater(() -> {

                new Controller(ui, client);

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        // powershell -ExecutionPolicy ByPass -File "c:\Users\alfre\Documents\Skola\OOA\new\TotalyNotDiscord\server\database\databasesetup\setupdatabase.ps1"
        // java -cp ".;server/database/pgjdbc-ng-all-0.8.9.jar" .\resources\sockets\ChatServer.java
    }
}
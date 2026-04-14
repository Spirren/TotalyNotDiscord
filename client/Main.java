package client;

import client.ClientServices.CDispatchContext;
import client.appinterface.Interface;
import client.appinterface.view.ChatWindow;
import client.observer.MessageHandler;
import client.sockets.ChatClient;
import java.io.IOException;
import javax.swing.SwingUtilities;
import resources.model.dispatcher.Dispatcher;

public class Main {

    public static void main(String[] args) {

        ChatWindow chatWindow = new ChatWindow();
        Interface ui = new Interface(chatWindow);

        MessageHandler messageHandler = new MessageHandler(chatWindow);
        try {
            Dispatcher dispatcher = new Dispatcher();
            ChatClient client = new ChatClient("localhost", 5000, messageHandler, dispatcher);
            new CDispatchContext(client, messageHandler, ui, dispatcher);
            client.start();

            // Always start Swing UI on Event Dispatch Thread
            SwingUtilities.invokeLater(() -> {

                new Controller(ui, client, chatWindow);

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        // powershell -ExecutionPolicy ByPass -File "c:\path\to\setupdatabase.ps1"
        // java -cp ".;server/database/pgjdbc-ng-all-0.8.9.jar" .\resources\sockets\ChatServer.java
    }
}
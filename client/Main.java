package client;

import javax.swing.SwingUtilities;

import client.appinterface.Interface;
import resources.model.MessageHandler;

public class Main {

    public static void main(String[] args) {

        Interface ui = new Interface();

        // Always start Swing UI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {

            new Controller(ui);

        });

        MessageHandler messageHandler = new MessageHandler(ui);
    }
}
package client;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        // Always start Swing UI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {

            new Controller();

        });
    }
}
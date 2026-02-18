package server.database;

public class StartListener {
    public static void main(String[] args) throws Exception {
        new DatabaseListener(new MessageDataReciver(), "new_message").run();
    }
}
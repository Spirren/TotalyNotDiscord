package server.database;

public class StartListener {
    public static void main(String[] args) throws Exception {
        new StreamListener(new MessageDataReciver(), new PostGressStreamSubscriber(new PostgresConnectionProvider()),
                "new_message").run(); // asynchronously
    }
}
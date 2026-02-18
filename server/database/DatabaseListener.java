package server.database;

import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.api.jdbc.PGNotificationListener;
import java.sql.Connection;

public class DatabaseListener implements Runnable {
    IDataReciver DataReciver;
    String channel;

    public DatabaseListener(IDataReciver DataReciver, String channel) {
        this.DataReciver = DataReciver;
        this.channel = channel;
    }

    public void run() {
        try (Connection conn = SqlManager.getListenerConnection()) {
            PGConnection pgConn = conn.unwrap(PGConnection.class);
            // Register a listener for notifications
            pgConn.addNotificationListener(new PGNotificationListener() {
                @Override
                public void notification(int processId, String channelName, String payload) {
                    DataReciver.reciveData(processId, channelName, payload);
                }
            });

            SqlUtils.addListener(conn, channel);
            System.out.println(String.format("Listening for notifications... on channel: %s ", channel));
            Thread.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            // This handles the "Exception is not compatible" error
            System.err.println("Database Ear crashed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
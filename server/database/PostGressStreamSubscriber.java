package server.database;

import java.sql.Connection;
import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.api.jdbc.PGNotificationListener;

import server.database.interfaces.IDataReceiver;
import server.database.interfaces.IEventStreamSubscriber;
import server.database.interfaces.IListenerConnectionProvider;

public class PostGressStreamSubscriber implements IEventStreamSubscriber {
    private IListenerConnectionProvider provider;

    public PostGressStreamSubscriber(IListenerConnectionProvider connectionProvider) {
        this.provider = connectionProvider;
    }

    @Override
    public void subscribe(String channel, IDataReceiver receiver) {
        try (Connection connection = provider.getListenerConnection()) {
            PGConnection pgConn = connection.unwrap(PGConnection.class);
            pgConn.addNotificationListener(new PGNotificationListener() {
                @Override
                public void notification(int processId, String channelName, String payload) {
                    receiver.reciveData(processId, channelName, payload);
                }
            });
            new SqlUtils().addListener(connection, "new_message");
            System.out.println(String.format("Listening on posgress channel: %s", channel));
            Thread.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            System.err.println("Database Ear crashed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
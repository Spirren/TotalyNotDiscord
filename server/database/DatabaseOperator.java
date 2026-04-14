package server.database;
import server.database.interfaces.IDatabaseOperator;



public class DatabaseOperator {
    private IDatabaseOperator databaseOperator;
    private static DatabaseOperator instance;

    private DatabaseOperator(IDatabaseOperator databaseOperator) {
        this.databaseOperator = databaseOperator;
    }

    public static IDatabaseOperator getInstance() {
        if (instance == null) {
            throw new IllegalStateException("DatabaseOperator must be initialized first!");
        }
        return instance.databaseOperator;
    }

    public static void setInstance(IDatabaseOperator databaseOperator) {
        if (instance != null) {
            throw new IllegalStateException("DatabaseOperator already instantiated");
        }
        instance = new DatabaseOperator(databaseOperator);
    }
}

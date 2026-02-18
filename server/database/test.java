package server.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

// java -cp ".;server/database/pgjdbc-ng-all-0.8.9.jar" server.database.test 
// javac -cp ".;server/database/pgjdbc-ng-all-0.8.9.jar" server/database/Test.java 
public class test {
    public static void main(String[] args) {
        PostgresConnectionProvider provider = new PostgresConnectionProvider();
        try (Connection conn = provider.getConnection()) {
            ArrayList<Integer> usersList = SqlUtils.getUsers(conn, 1);
            for (int id : usersList) {
                System.out.println("userId" + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

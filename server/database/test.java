package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// java -cp ".;server/database/pgjdbc-ng-all-0.8.9.jar" server.database.test 
// javac -cp ".;server/database/pgjdbc-ng-all-0.8.9.jar" server/database/Test.java 
public class test {
    public static void main(String[] args) {
        try (Connection conn = SqlManager.getConnection()) {
            SqlUtils.viewTable(conn, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

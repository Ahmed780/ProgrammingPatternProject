package progproject;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Uzair Lakhani, Muhammad Val
 */
public class DBConnection {
    
    /**
     * This method establishes database connection
     *
     * @return database connection
     */
    public static Connection DbConnector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\user01\\Downloads\\SQLlite\\db\\client2.db");

            return c;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

}

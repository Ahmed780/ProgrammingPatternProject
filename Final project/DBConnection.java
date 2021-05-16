package Progproject;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Uzair Lakhani, Muhammad Val
 */
public class DBConnection {
    
private static Connection con;
    public static Connection getInstance() {
        if (con == null) {
            con = DbConnector();
        }
        return con;
    }

    public static Connection DbConnector() {
        String db = "jdbc:sqlite:C:\\Users\\user01\\Downloads\\SQLlite\\db\\Project.db";
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(db);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return c;
    }

}

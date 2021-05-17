package ProgPatternProject;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Uzair Lakhani, Mohamed Mohamed Vall
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
        String db = "jdbc:sqlite:C:\\Users\\user01\\Downloads\\SQLlite\\db\\d.db";
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

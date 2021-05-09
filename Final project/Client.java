package progproject;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Uzair Lakhani, Muhammad Val
 */
public class Client {

    private String fullName;
    private String passNumber;
    private String contact;

    public Client(String fullName, String passNumber, String contact) {
        this.fullName = fullName;
        this.passNumber = passNumber;
        this.contact = contact;
    }

    public boolean bookASeat(String fn) {
        try (Connection con = DBConnection.DbConnector();
                Statement stmt = con.createStatement()) {
            String createTable = "INSERT INTO Clients (fullName, passNumber, contact) "
                    + "VALUES (" + passNumber + ", '" + fullName + "',' "
                    + contact + "');";
            System.out.println("sql: " + createTable);
            stmt.execute(createTable);
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return false;
    }

    public boolean cancelResservation(int ticket) {

        return false;
    }

    public List<Flight> searchFlightByDest(String dest) {

        return null;
    }

    public List<Flight> searchFlightByDuration(int d) {

        return null;
    }

    public List<Flight> searchFlighByOrigin(String origin) {

        return null;
    }

    public Map<String, String> viewFlightsBoard() {

        return null;
    }
}

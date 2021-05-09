package progproject;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;

/**
 *
 * @author Uzair Lakhani, Muhammad Val
 */
public class Flight {

    private String flightN;
    private String name;
    private String origin;
    private String dest;
    private double duration;
    private int seats;
    private boolean available;
    private double amount;

    public Flight(String flightN, String name, String origin, String destination, double duration, int seats, boolean available, double amount) {
        this.flightN = flightN;
        this.name = name;
        this.origin = origin;
        this.dest = destination;
        this.duration = duration;
        this.seats = seats;
        this.available = available;
        this.amount = amount;
    }
    
    public boolean addFlight(Flight flight) {
        try (Connection con = DBConnection.DbConnector();
                Statement stmt = con.createStatement()) {
            
            String createTable = "INSERT INTO Flights (flightN, name, origin, destination, "
                    + "duration, seats, available, amount) "
                    + "VALUES (" + flight.flightN + ", '" + flight.name + "',' "
                    + flight.origin + "',' " + flight.dest + "',' "
                    + flight.duration + "',' " + flight.seats + "',' "
                    + flight.available + "',' " + flight.amount + "');";
            System.out.println("sql: " + createTable);
            stmt.execute(createTable);
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return false;
    }

    public boolean removerFlight(String flightN) {

        return false;
    }

    public boolean updateFlightData(String flightN, String field, String newValue) {

        return false;
    }

    public boolean issueTicket(Client c) {

        return false;
    }

    public boolean cancelFlight(int ticket, int passN) {

        return false;
    }

    public static Map<String, String> viewBoard() {

        return null;
    }

    public static Map<String, String> viewBookedFlights() {

        return null;
    }

}

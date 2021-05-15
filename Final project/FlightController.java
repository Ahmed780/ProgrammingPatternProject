package progproject;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Uzair Lakhani, Muhammad Val
 */
public class FlightController {
    
    private List<Flight> model;
    private FlightView view;
    
    public FlightController (List<Flight> model, FlightView view) {
        this.model = model;
        this.view = view;
    }
    
    public void createFlightsTable(String createTable) {
        try (Connection con = DBConnection.DbConnector();
                Statement stmt = con.createStatement()){
          stmt.executeUpdate("DROP TABLE IF EXISTS Flights;");
            stmt.executeUpdate(createTable); 
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
      public boolean addFlight(Flight flight) {
        try (Connection con = DBConnection.DbConnector();
                Statement stmt = con.createStatement()) {
           
            String createTable = "INSERT INTO Flights (flightN, name, origin, dest, "
                    + "duration, seats, available, amount) "
                    + "VALUES (" + flight.getFlightN() + ", '" + flight.getName() + "',' "
                    + flight.getOrigin() + "',' " + flight.getDest() + "',' "
                    + flight.getDuration() + "',' " + flight.getSeats() + "',' "
                    + flight.isAvailable() + "',' " + flight.getAmount() + "');";
            System.out.println("Inserting data " + createTable);
            stmt.executeUpdate(createTable);
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return false;
    }
        public boolean removerFlight(String flightN) {
          try (Connection con = DBConnection.DbConnector();
                Statement stmt = con.createStatement()) {           
            String createTable = "DELETE FROM Flights WHERE flightN= '1001';"+ flightN;
              System.out.println("\ndelete from Flights where flightN");
            stmt.execute(createTable);
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return false;
    }
        public Map<Integer, String> getAllFlights() {
        Map<Integer, String> map = new HashMap();

        try (Connection con = DBConnection.DbConnector();
         Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * from Flights;");
            while (rs.next()) {
                //int id = rs.getInt("STUDENTID");
                String name = rs.getString("name");
                String addr = rs.getString("origin");
                map.put(rs.getInt("flightN"), " name: " + name + ", origin: " + addr);
            }
               } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return map;
    }
            
    public void createClientsTable(String sqlStatement) {
         try (Connection con = DBConnection.DbConnector();
                Statement stmt = con.createStatement()){
            stmt.executeUpdate("DROP TABLE IF EXISTS Clients;");
            stmt.executeUpdate(sqlStatement); 
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public void createReservedFlightsTable(String sqlStatement) {
         try (Connection con = DBConnection.DbConnector();
                Statement stmt = con.createStatement()){
            stmt.executeUpdate("DROP TABLE IF EXISTS ReservedFlights;");
            stmt.executeUpdate(sqlStatement); 
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public void updateView(Map model) {
        FlightView.printFlightDetails(model);
    }
}

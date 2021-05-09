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
    
    public void createFlightsTable(String sqlStatement) {
        try (Connection con = DBConnection.DbConnector();
                Statement stmt = con.createStatement()){
            stmt.executeUpdate("DROP TABLE IF EXIST Flights;");
            stmt.executeUpdate(sqlStatement); 
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public void createClientsTable(String sqlStatement) {
         try (Connection con = DBConnection.DbConnector();
                Statement stmt = con.createStatement()){
            stmt.executeUpdate("DROP TABLE IF EXIST Clients;");
            stmt.executeUpdate(sqlStatement); 
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public void createReservedFlightsTable(String sqlStatement) {
         try (Connection con = DBConnection.DbConnector();
                Statement stmt = con.createStatement()){
            stmt.executeUpdate("DROP TABLE IF EXIST ReservedFlights;");
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

package Progproject;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Uzair Lakhani, Muhammad Val
 */
public class FlightController {
    
    private List<Flight> model;
    private FlightView view;
      private final Connection con = DBConnection.getInstance();
    
    public FlightController (List<Flight> model, FlightView view) {
        this.model = model;
        this.view = view;
    }
    
    public void createFlightsTable(String createTable) {
        try (Statement stmt = con.createStatement()){
          stmt.executeUpdate("DROP TABLE IF EXISTS Flights;");
            stmt.executeUpdate(createTable); 
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    } 
    public void createReservedFlightsTable(String sqlStatement) {
        try (Statement stmt = con.createStatement()) {
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

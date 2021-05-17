package ProgPatternProject;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

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
    private int available;
    private double amount;
    public static int ticketN;
    private static Connection con = DBConnection.getInstance();

    public Flight(String flightN, String name, String origin, String dest, double duration, int seats, int available, double amount) {
        this.flightN = flightN;
        this.name = name;
        this.origin = origin;
        this.dest = dest;
        this.duration = duration;
        this.seats = seats;
        this.available = seats;
        this.amount = amount;
    }

    public Flight() {
    }

    /**
     *
     * @param flight
     * @return
     */
    public boolean addFlight(Flight flight) {
         flight.available = flight.seats;
        try (Statement stmt = con.createStatement()) {
            String createTable = "INSERT INTO Flights (flightN, name, origin, dest, "
                    + "duration, seats, available, amount) " + "VALUES (" + flight.flightN + ", '" + flight.name + "',' "
                    + flight.origin + "',' " + flight.dest + "',' "
                    + flight.duration + "',' " + flight.seats + "',' "
                    + flight.available + "',' " + flight.amount + "');";
            stmt.execute(createTable);
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return false;
    }

    /**
     *
     * @param flightN
     * @return
     */
    public boolean removeFlight(String flightN) {
        try (Statement stmt = con.createStatement()) {
            String createTable = "delete from Flights where flightN =" + flightN + ";";
            stmt.execute(createTable);
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return false;
    }

    /**
     *
     * @param flightN
     * @param field
     * @param newValue
     * @return
     */
    public boolean updateFlightData(String flightN, String field, String newValue) {
        try (Statement stmt = con.createStatement()) {
            String createTable = "UPDATE Flights SET " + field + "= " + "'" + newValue + "'"
                    + " where flightN = " + flightN + ";";
            stmt.execute(createTable);
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return false;
    }
    
    /**
     *
     * @param ticket
     * @param passN
     * @return
     */
    public boolean cancelFlight(int ticket, int passN) {
        try (Statement stmt = con.createStatement()) {
           String createTable = "DELETE FROM reservedFlights WHERE ticketN= '" + ticket +
                    "' AND PassNum= '" + passN + "';";
            stmt.execute(createTable);
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return false;
    }
     
    /**
     *
     * @return
     */
    public static Map<String, String> viewBoard() {
        Map<String, String> map = new HashMap();

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Flights;");

            while (rs.next()) {
                String name = rs.getString("name");
                String origin = rs.getString("origin");
                String dest = rs.getString("dest");
                int duration = rs.getInt("duration");
                int seats = rs.getInt("seats");
                int available = rs.getInt("available");
                int amount = rs.getInt("amount");

                map.put(rs.getString("flightn"), " name: " + name + ", origin: "
                        + origin + ", dest: " + dest + ", duration: " + duration
                        + ", seats: " + seats + ", available: " + available
                        + ", amount: " + amount + "\n");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return map;
    }
     
    /**
     *
     * @return
     */
    public static Map<String, String> viewBookedFlights() {
        Map<String, String> map = new HashMap();

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM reservedFlights;");

            while (rs.next()) {
                String flightN = rs.getString("flightN");
                String passNum = rs.getString("PassNum");
                String flName = rs.getString("FLname");
                int issueDate = rs.getInt("IssueDate");
                int contact = rs.getInt("Contact");
                int amount = rs.getInt("Amount");
                map.put(rs.getString("ticketN"), " flightN: " + flightN + ", PassNum: " + passNum + ", FLname: " + flName + ", IssueDate: " + issueDate + ", Contact: " + contact
                + ", Amount: " + amount + "\n");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return map;
    }
    
    /**
     *
     * @param c
     * @param flight
     * @return
     */
    
    public boolean issueTicket(Client c, String flight) {       
        try (Statement stmt = con.createStatement()) {           
            ResultSet rs = stmt.executeQuery("SELECT * FROM Flights " + "WHERE flightN= " + flight + ";");
            while (rs.next()) {
                String flightNF = rs.getString("flightN");
                int amountF = rs.getInt("Amount");
                int availableSeats = rs.getInt("Available");                
                if (availableSeats > 0) {
                    ticketN++;
                    try (Statement stmt2 = con.createStatement()) {
                        String createTable = "INSERT INTO reservedFlights (ticketN, flightN, "+ "PassNum, FLname, IssueDate, Contact, Amount) "
                                + "VALUES ('" + ticketN + "', '" +  flightNF + "', '"
                                + c.getPassNumber() + "', '" + c.getFullName() + "', '"
                                + (LocalDateTime.now()) + "', '" 
                                + c.getContact() + "', '" + amountF + "');";
                        stmt2.execute(createTable);
                        return true;
                    } catch (Exception e) {
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                        System.exit(0);
                    }
                }
            }
        }
        catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
         }

        return false;
    }

    public String getFlightN() {
        return flightN;
    }

    public void setFlightN(String flightN) {
        this.flightN = flightN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static int getTicketN() {
        return ticketN;
    }

    public static void setTicketN(int ticketN) {
        Flight.ticketN = ticketN;
    }

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        Flight.con = con;
    }

    @Override
    public String toString() {
        return "Flight{" + "flightN=" + flightN + ", name=" + name + ", origin=" + origin + ", dest=" + dest + ", duration=" + duration + ", seats=" + seats + ", available=" + available + ", amount=" + amount + '}';
    }
    
    
    
   
}

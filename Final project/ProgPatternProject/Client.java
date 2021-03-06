package ProgPatternProject;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Uzair Lakhani, Mohamed Mohamed Vall
 */
public class Client {

    protected String fullName;
    protected int passNumber;
    protected long contact;
    private static Connection con = DBConnection.getInstance();
    Flight flight = new Flight();
    
    public Client(String fullName, int passNumber, long contact) {
        this.fullName = fullName;
        this.passNumber = passNumber;
        this.contact = contact;
    }

    public Client() {
    }
    
    public boolean bookASeat(String flightNum) {

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Flights "
                    + "WHERE flightN= " + flightNum + ";");
            while (rs.next()) {
                int available = rs.getInt("Available");
                if (available > 0) {
                    available--;
                }
            }
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return false;
    }

    /**
     *Uses sql command to go through the table and delete the entry with the matching ticket
     * @param ticket, the number of the ticket to be deleted
     * @return True if the table was updated, false if it wasn't
     */
    public boolean cancelResservation(int ticket) {

        try (Statement stmt = con.createStatement()) {
            String createTable = "DELETE FROM reservedFlights WHERE ticketN=" + ticket + ";";
            stmt.execute(createTable);
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return false;
    }

    /**
     *Goes through the Flights table, saving each row's data and, through the destination 
     *attribute, seeing if they match with the destination inserted in the parameter. Every
     *matching flight is saved in a list
     * @param dest, the destination the flights should match
     * @return List of  flights with the same destination
     */
    public List<Flight> searchFlightByDest(String dest) {
        List<Flight> flightList = new ArrayList<>();
        Flight flightDest;

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Flights");
            while (rs.next()) {
                String destination = rs.getString("dest");
                String flightN = rs.getString("flightN");
                String name = rs.getString("name");
                String origin = rs.getString("origin");
                int duration = rs.getInt("duration");
                int amount = rs.getInt("amount");
                int seats = rs.getInt("seats");

                if (destination.equals(dest)) {
                    flightDest = new Flight(flightN, name, origin, destination, duration, seats, amount);
                    flightList.add(flightDest);
                }
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return flightList;
    }

    /**
     *Goes through the Flights table, saving each row's data and, through the duration 
     *attribute, seeing if they match with the destination inserted in the parameter. Every
     *matching flight is saved in a list
     * @param d, the duration the flights should match
     * @return List of  flights with the same duration
     */
    public List<Flight> searchFlightByDuration(int d) {
        List<Flight> flightList = new ArrayList<>();
        Flight flightdur;

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Flights");

            while (rs.next()) {
                String destination = rs.getString("dest");
                String flightN = rs.getString("flightN");
                String name = rs.getString("name");
                String origin = rs.getString("origin");
                int duration = rs.getInt("duration");
                int amount = rs.getInt("amount");
                int seats = rs.getInt("seats");
                if (duration == d) {
                    flightdur = new Flight(flightN, name, origin, destination, duration, seats, amount);

                    flightList.add(flightdur);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return flightList;
    }

    /**
     *Goes through the Flights table, saving each row's data and, through the origin 
     *attribute, seeing if they match with the destination inserted in the parameter. Every
     *matching flight is saved in a list
     * @param dest, the origin the flights should match
     * @return List of  flights with the same origin
     */
    public List<Flight> searchFlighByOrigin(String orig) {
        List<Flight> originList = new ArrayList<>();
        Flight flightList = null;

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Flights");
            while (rs.next()) {
                String destination = rs.getString("dest");
                String flightN = rs.getString("flightN");
                String name = rs.getString("name");
                String origin = rs.getString("origin");
                int duration = rs.getInt("duration");
                int amount = rs.getInt("amount");
                int seats = rs.getInt("seats");

                if (origin.equals(orig)) {
                    flightList = new Flight(flightN, name, origin, destination, duration, seats, amount);

                    originList.add(flightList);
                }
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return originList;
    }

    /**
     * Uses a sql command to receive every entry of the clients table. Through a while loop,
     * every entry is saved in a list.
     * @return List of all the clients
     */
    public static Map<String, String> viewBoard() {
        Map<String, String> map = new HashMap();

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Clients;");
            while (rs.next()) {
                String fullName = rs.getString("FLname");
                int contact = rs.getInt("Contact");
                map.put(rs.getString("PassNum"), " FLname: " + fullName + ", Contact: "
                        + contact + "\n");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return map;
    }

    /**
     * Uses a sql command to receive every entry of the Flights table. Through a while loop,
     * every entry is saved in a list.
     * @return List of all the flights
     */
    public Map<String, String> viewFlightsBoard() {
        Map<String, String> map = new HashMap();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM reservedFlights;");
            while (rs.next()) {
                String flightN = rs.getString("flightN");
                String passNum = rs.getString("PassNum");
                String flName = rs.getString("FLname");
                int issueDate = rs.getInt("IssueDate");
                int cont = rs.getInt("Contact");
                int amount = rs.getInt("Amount");
                map.put(rs.getString("ticketN"), " flightN: " + flightN
                        + ", PassNum: " + passNum + ", FLname: " + flName
                        + ", IssueDate: " + issueDate + ", Contact: " + cont
                        + ", Amount: " + amount + "\n");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return map;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(int passNumber) {
        this.passNumber = passNumber;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }
}

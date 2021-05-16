package Progproject;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Uzair Lakhani, Muhammad Val
 */
public class Client {

    private String fullName;
    private int passNumber;
    private int contact;
     private static Connection con = DBConnection.getInstance();

    public Client(String fullName, int passNumber, int contact) {
        this.fullName = fullName;
        this.passNumber = passNumber;
        this.contact = contact;
    }
        public Client() {
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

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public boolean bookASeat(String fn) {
        try (Statement stmt = con.createStatement()) {
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

    public List<Flight> searchFlightByDuration(int d) {
        List<Flight> flightList = new ArrayList<>();
        Flight flightdur;

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM FLIGHTS");

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
}

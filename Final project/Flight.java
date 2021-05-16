package progproject;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author Uzair Lakhani, Mohamed Mohamed Vall
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

    public Flight(String flightN, String name, String origin, String dest, double duration, int seats, double amount) {
        this.flightN = flightN;
        this.name = name;
        this.origin = origin;
        this.dest = dest;
        this.duration = duration;
        this.seats = seats;
        this.amount = amount;
    }
     public Flight() {
    }
     
    public boolean addFlight(Flight flight) {
        try (Connection con = DBConnection.DbConnector();
                Statement stmt = con.createStatement()) {
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

    public boolean removerFlight(String flightN) {
        try (Connection con = DBConnection.DbConnector();
                Statement stmt = con.createStatement()) {
            String createTable = "delete from Flights where flightN =" + flightN + ";";
            stmt.execute(createTable);
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return false;
    }
      public static Map<String, String> viewBoard() {
        Map<String, String> map = new HashMap();

        try (Connection con = DBConnection.DbConnector();
                Statement stmt = con.createStatement()) {
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

    public boolean updateFlightData(String flightN, String field, String newValue) {
        try (Connection con = DBConnection.DbConnector();
                Statement stmt = con.createStatement()) {
            String sql = "UPDATE Flights SET " + field + "= " + "'" + newValue + "'"
                    + " where flightN = " + flightN + ";";
            stmt.execute(sql);
            return true;
        } catch (Exception e) {
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Flight{" + "flightN=" + flightN + ", name=" + name + ", origin=" + origin + ", dest=" + dest + ", duration=" + duration + ", seats=" + seats + ", available=" + available + ", amount=" + amount + '}';
    }

}

package progproject;

import java.sql.Connection;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author Uzair Lakhani, Muhammad Val
 */
public class UseFlight {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        List<Flight> model = retrieveData();
        FlightView view = new FlightView();
        FlightController controller = new FlightController(model, view);
         Flight flight = new Flight();
        
         List<Client> clientModel = retrieveClientData();
        ClientView clientView = new ClientView();
        ClientController clientController = new ClientController(clientModel, clientView);
        Client client = new Client();
        
        String fligtTable = "CREATE TABLE Flights "
                + "(flightN TEXT PRIMARY KEY NOT NULL,"
                + " name                TEXT  NOT NULL, "
                + "origin              TEXT NOT NULL, "
                + "dest                TEXT NOT NULL, "
                + " duration            INT  NOT NULL, "
                + " seats               INT   NOT NULL, "
                + " available           INT NOT NULL, "
                + " amount              INT  NOT NULL)";   
                
    String clientTable = "CREATE TABLE Clients "
            + " (FLname                TEXT NOT NULL,"
            + "Passnum int PRIMARY KEY     NOT NULL,"
            + " Contact   INT NOT NULL)";

        String reservedTable = "CREATE TABLE reservedFLIGHTS "
                + "(TicketN INT PRIMARY KEY       NOT NULL,"
                + " FlightN                  TEXT NOT NULL,"
                + " PassNum                   INT NOT NULL,"
                + " FLname                   TEXT NOT NULL,"
                + " IssueDate                DATE NOT NULL,"
                + " Contact                   INT NOT NULL,"
                + " Amount                    INT NOT NULL,"
                + " CONSTRAINT FK_RESERVE_FLIGHN FOREIGN KEY(FlightN)"
                + " REFERENCES FLIGHTS(FLIGHTN),"
                + " CONSTRAINT FK_RESERVE_PASSNUM FOREIGN KEY(PassNum)"
                + " REFERENCES CLIENTS(PASSNUM))";
        
        controller.createFlightsTable(fligtTable );
        System.out.println("TABLE FLIGHTS CREATED");
        clientController.createClientsTable(clientTable);
        System.out.println("TABLE CLIENTS CREATED");
        controller.createReservedFlightsTable(reservedTable);
        System.out.println("TABLE RESERVEDFLIGHTS CREATED");
        
        System.out.println("\nView Board");
        model.forEach((fl) -> {
            flight.addFlight(fl);
        });
        controller.updateView(Flight.viewBoard());

    }

    /**
     *this method retrieves from flight table
     * @return ArrayList 
     */

    public static List<Flight> retrieveData() {
        Flight[] flightsList = {
            new Flight("1001", "Boeing 737 Max", "Montreal", "Amsterdam", 555, 204, 1115),
            new Flight("1002", "Boeing 737", "Toronto", "London", 405, 130, 635),
            new Flight("1003", "Boeing 787 Breamliner", "Montreal", "Doha", 725, 248, 3093),
            new Flight("1004", "Boeing 800", "Toronto", "Guyana", 1200, 168, 721)};

        return new ArrayList(Arrays.asList(flightsList));
    }

     /**
     *this method retrieves from client table 
     * @return ArrayList 
     */
     
        public static List<Client> retrieveClientData() {
        Client[] clientList = {
            new Client("Uzair Lakhani",5001, 786),
            new Client("Muhmmad Val",5002, 455),
            new Client("Uraib Lakhani",5003, 999),
            new Client("Rafey Lakhani",5004, 555)};

        return new ArrayList(Arrays.asList(clientList));
    }       
}

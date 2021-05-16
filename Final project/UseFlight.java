package Progproject;

import java.util.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Uzair Lakhani, Muhammad Val
 */
public class UseFlight {

    public static void main(String[] args) {

        List<Flight> model = retrieveData();
        FlightView view = new FlightView();
        FlightController controller = new FlightController(model, view);
        Flight flight = new Flight();

        List<Client> clientModel = retrieveClientData();
        ClientView clientView = new ClientView();
        ClientController clientController = new ClientController(clientModel, clientView);
        Client client = new Client();

        Locale locale1 = new Locale("en", "CA");
        Locale locale2 = new Locale("fr", "CA");
        Scanner sc = new Scanner(System.in);
        ResourceBundle res = ResourceBundle.getBundle("Progproject/file_en_GB", locale1);
        ResourceBundle res2 = ResourceBundle.getBundle("Progproject/file_fr_CA", locale2);

        System.out.println("Choose a Locale from 1 to 2: ");
        System.out.println("1: Canada, English\n"
                + "2: Canada, French\n");

        switch (sc.nextInt()) {
            case 1:
                System.out.println(res.getString("key1"));
                System.out.println(res.getString("key2"));
                System.out.println(res.getString("key3"));
                 System.out.println("\n"+res.getString("key4"));
                break;
            case 2:
                System.out.println(res2.getString("key1"));
                System.out.println(res2.getString("key2"));
                System.out.println(res2.getString("key3"));
                 System.out.println("\n"+ res2.getString("key4"));
                break;
            default:
                System.out.println("Invalid choice");
        }

        String fligtTable = "CREATE TABLE Flights "
                + "(flightN varchar(25) primary key,"
                + " Name    varchar(25)  NOT NULL, "
                + "Origin   varchar(25) NOT NULL, "
                + "Dest     varchar(25) NOT NULL, "
                + "Duration INT, "
                + "Seats     INT, "
                + "Available  INT NOT NULL, "
                + "Amount    INT)";

        String clientTable = "CREATE TABLE Clients "
                + " (FLname                TEXT NOT NULL,"
                + "Passnum int PRIMARY KEY     NOT NULL,"
                + " Contact   INT NOT NULL)";

        String reservedTable = "CREATE TABLE reservedFlights "
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
 
        controller.createFlightsTable(fligtTable);
        clientController.createClientsTable(clientTable);
        controller.createReservedFlightsTable(reservedTable);

        model.forEach((fl) -> {
            flight.addFlight(fl);
        });
        controller.updateView(Flight.viewBoard());

    }

    /**
     * This method retrieves data from flight table
     *
     * @return ArrayList
     */
    public static List<Flight> retrieveData() {
        Flight[] flightsList = {
            new Flight("2490", "Boeing 747-400", "Montreal", "Pakistan", 1000, 200, 1115),
            new Flight("2491", "Boeing 777-300", "Toronto", "Morocco", 800, 250, 635),
            new Flight("2492", "Airbus A340-600", "Ottawa", "Turkey", 750, 150, 3093),
            new Flight("2493", "Airbus A350-900", "Ontario", "Qatar", 500, 100, 721)};

        return new ArrayList(Arrays.asList(flightsList));
    }

    /**
     * this method retrieves from client table
     *
     * @return ArrayList
     */
    public static List<Client> retrieveClientData() {
        Client[] clientList = {
            new Client("Uzair Lakhani", 54325, 514-555-5465),
            new Client("Muhmmad Val", 54636, 514-555-67565),
            new Client("Uraib Lakhani", 34555, 438-555-0157),
            new Client("Rafey Lakhani", 53456, 613-555-0154)};
         return new ArrayList(Arrays.asList(clientList));
    }
}

package ProgPatternProject;

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
        ResourceBundle res = ResourceBundle.getBundle("ProgPatternProject/file_en_GB", locale1);
        ResourceBundle res2 = ResourceBundle.getBundle("ProgPatternProject/file_fr_CA", locale2);

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
                + " (FLname varchar(25) ,"
                + "PassNum int PRIMARY KEY,"
                + " Contact INT )";

        String reservedTable = "CREATE TABLE reservedFlights "
                + "(ticketN INT PRIMARY KEY,"
                + " flightN  varchar(25),"
                + " PassNum  varchar,"
                + " FLname   varchar(25),"
                + " IssueDate DATE,"
                + " Contact INT,"
                + " Amount INT,"
                + " CONSTRAINT FK_RESERVE_FLIGHN FOREIGN KEY(FlightN)"
                + " REFERENCES Flights(FLIGHTN),"
                + " CONSTRAINT FK_RESERVE_PASSNUM FOREIGN KEY(PassNum)"
                + " REFERENCES Clients(PassNum))";

        System.out.println("Are you a manager or client?");
        System.out.println("1: Manager, English\n"
                        + "2: Manager, French\n"
                        + "3: Client, English\n"
                        + "4: Client, French\n"
        );

        switch (sc.nextInt()) {
            case 1:
                System.out.println(res.getString("key1"));
                controller.createFlightsTable(fligtTable);
                controller.createReservedFlightsTable(reservedTable);
                   for (Flight fl : model) {
                     flight.addFlight(fl);
                 }
                controller.updateView(Flight.viewBoard());

                //Remove flight
                System.out.println(res.getString("key5"));
                flight.removeFlight("2490");
                controller.updateView(Flight.viewBoard());

                //Update flight
                System.out.println(res.getString("key6"));
                flight.updateFlightData("2491", "origin", "Montreal");
                                
                controller.updateView(Flight.viewBoard());
                
                //Issue tickets
                System.out.println("\n" + res.getString("key9"));
                
                clientModel.forEach((cl) -> {
                    if (cl.getPassNumber() == 34555) {
                        flight.issueTicket(cl, "2491");
                    }
                });
                controller.updateView(Flight.viewBookedFlights());

                clientModel.forEach((cl) -> {
                    if (cl.getPassNumber() == 53456) {
                        flight.issueTicket(cl, "2491");
                    }
                });
                controller.updateView(Flight.viewBookedFlights());

                break;

            case 2:
                controller.createFlightsTable(fligtTable);
                System.out.println(res.getString("key1"));
                System.out.println(res.getString("key2"));

                model.forEach((fl) -> {
                    flight.addFlight(fl);
                });
                controller.updateView(Flight.viewBoard());
                //Remove flight
                System.out.println("\nRemoving flight number 2490");
                flight.removeFlight("2490");
                controller.updateView(Flight.viewBoard());
                //Update flight
                System.out.println("\nupdating flight");
                flight.updateFlightData("2491", "origin", "Montreal");
                controller.updateView(Flight.viewBoard());

                clientController.updateView(Client.viewBoard());

                controller.updateView(Flight.viewBookedFlights());
                controller.createReservedFlightsTable(reservedTable);
                break;
                
            case 3:
                
                clientController.createClientsTable(clientTable);
                 System.out.println(res2.getString("key2"));
                    for (Client cl : clientModel) {
                    clientController.addClient(cl);
                 }
                clientController.updateView(Client.viewBoard());

               // controller.updateView(Flight.viewBookedFlights());

                //Cancel flight
                System.out.println("\nCancel flight");
                flight.cancelFlight(1, 54325);
                controller.updateView(Flight.viewBookedFlights());

                //Search by destination
                System.out.println("\nSearching by destination");
                client.searchFlightByDest("Turkey").forEach(dest -> {
                    System.out.println(dest.toString());
                });

                //Search by duration
                System.out.println("\nSearching by duration");
                client.searchFlightByDuration(500).forEach(dur -> {
                    System.out.println(dur.toString());
                });

                //Search by origin
                System.out.println("\nSearching by origin");
                client.searchFlighByOrigin("Ontario").forEach(ori
                        -> {
                    System.out.println(ori.toString());
                });
                
                //Book a flight
                System.out.println("\nBooking a flight");
                clientModel.forEach((cl) -> {
                    if (cl.getPassNumber() == 34555) {
                        if (client.bookASeat("2493") == true) {
                            flight.issueTicket(cl, "2493");
                        }
                    }
                });
                
                controller.updateView(Flight.viewBookedFlights());
                break;
                
                case 4:
                clientController.createClientsTable(clientTable);
                 System.out.println(res2.getString("key2"));
                    for (Client cl : clientModel) {
                    clientController.addClient(cl);
                 }
                clientController.updateView(Client.viewBoard());

               controller.updateView(Flight.viewBookedFlights());

                //Cancel flight
                System.out.println("\nCancel flight");
                flight.cancelFlight(1, 54325);
                controller.updateView(Flight.viewBookedFlights());

                //Search by destination
                System.out.println("\nSearching by destination");
                client.searchFlightByDest("Turkey").forEach(dest -> {
                    System.out.println(dest.toString());
                });

                //Search by duration
                System.out.println("\nSearching by duration");
                client.searchFlightByDuration(500).forEach(dur -> {
                    System.out.println(dur.toString());
                });

                //Search by origin
                System.out.println("\nSearching by origin");
                client.searchFlighByOrigin("Ontario").forEach(ori
                        -> {
                    System.out.println(ori.toString());
                });
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    /**
     * This method retrieves data from flight table
     *
     * @return List of flights
     */
    public static List<Flight> retrieveData() {
        Flight[] flightsList = {
            new Flight("2490", "Boeing 747-400", "Montreal", "Pakistan", 1000, 50, 200, 1115),
            new Flight("2491", "Boeing 777-300", "Toronto", "Morocco", 800, 250,150, 635),
            new Flight("2492", "Airbus A340-600", "Ottawa", "Turkey", 750, 150, 100, 3093),
            new Flight("2493", "Airbus A350-900", "Ontario", "Qatar", 500, 100, 20, 721)};

        return new ArrayList(Arrays.asList(flightsList));
    }

    /**
     * this method retrieves data from client table
     *
     * @return List of clients
     */
    public static List<Client> retrieveClientData() {
        Client[] clientList = {
            new Client("Uzair Lakhani", 54325, 514645435),
            new Client("Muhmmad Val", 54636,  514557567),
            new Client("Uraib Lakhani", 34555, 438565464),
            new Client("Rafey Lakhani", 53456, 613545345)};
        
        return new ArrayList(Arrays.asList(clientList));
    }
}

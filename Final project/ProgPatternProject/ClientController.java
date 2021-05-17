package ProgPatternProject;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Uzair Lakhani
 */
public class ClientController {
    private List<Client> model;
    private ClientView view;
      private final Connection con = DBConnection.getInstance();

    public ClientController(List<Client> model, ClientView view) {
        this.model = model;
        this.view = view;
    }
 
    /**
     *
     * @param createTable
     */
    public void createClientsTable(String createTable) {
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS Clients;");
            stmt.executeUpdate(createTable);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    /**
     *
     * @param client
     * @return
     */
    public void addClient(Client client) {
        try (Statement stmt = con.createStatement()) {
            String createTable = "INSERT INTO Clients (FLname,PassNum, Contact) "
                    + "VALUES ('" + client.fullName + "', '" + client.passNumber + "',' " + client.contact + "');";
            stmt.execute(createTable);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    /**
     *
     * @param model
     */
    public void updateView(Map model) {
        ClientView.printClientDetails(model);
    }
}

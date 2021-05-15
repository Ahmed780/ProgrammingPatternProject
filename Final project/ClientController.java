package progproject;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Uzair Lakhani
 */
public class ClientController {
    private List<Client> model;
    private ClientView view;

    public ClientController(List<Client> model, ClientView view) {
        this.model = model;
        this.view = view;
    }
 
    public void createClientsTable(String createTable) {
        try (Connection con = DBConnection.DbConnector();
                Statement stmt = con.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS CLIENTS;");
            stmt.executeUpdate(createTable);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public void updateView(Map model) {
        ClientView.printClientDetails(model);
    }
}

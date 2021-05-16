package Progproject;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Uzair Lakhani, Muhammad Val
 */
public class ClientController {
    private List<Client> model;
    private ClientView view;
      private final Connection con = DBConnection.getInstance();

    public ClientController(List<Client> model, ClientView view) {
        this.model = model;
        this.view = view;
    }
 
    public void createClientsTable(String createTable) {
        try (Statement stmt = con.createStatement()) {
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

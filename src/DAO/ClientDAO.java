package DAO;

import models.Client;
import models.Conseiller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    static Connection con = Database.getConnection();

    public void addClient(Client client) throws Exception {
        String sql = "INSERT INTO clients (id, nom, prenom, email, conseiller_id) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, client.getId());
        p.setString(2, client.getNom());
        p.setString(3, client.getPrenom());
        p.setString(4, client.getEmail());
        p.setString(5, client.getConseiller());

        p.executeUpdate();

    }
}







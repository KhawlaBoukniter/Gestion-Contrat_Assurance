package DAO;

import models.Client;
import models.Conseiller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public Boolean addClient(Client client) throws Exception {
        String sql = "INSERT INTO clients (id, nom, prenom, email, conseiller_id) VALUES (?, ?, ?, ?, ?)";

        Connection con = Database.getConnection();
        try (PreparedStatement p = con.prepareStatement(sql)) {
            p.setString(1, client.getId());
            p.setString(2, client.getNom());
            p.setString(3, client.getPrenom());
            p.setString(4, client.getEmail());
            p.setString(5, client.getConseiller());

            p.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                return false;
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    public Boolean deleteClient(String id) throws Exception {
        String sql = "DELETE FROM clients WHERE id = ?";

        Connection con = Database.getConnection();
        try (PreparedStatement p = con.prepareStatement(sql)) {
            p.setString(1, id);
            int rows = p.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Client> getAll() throws Exception {
        String sql = "SELECT * FROM clients";
        List<Client> clients = new ArrayList<>();

        Connection con = Database.getConnection();
        try (PreparedStatement p = con.prepareStatement(sql);
             ResultSet rs = p.executeQuery()) {
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getString("id"));
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setEmail(rs.getString("email"));
                client.setConseiller(rs.getString("conseiller_id"));

                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}







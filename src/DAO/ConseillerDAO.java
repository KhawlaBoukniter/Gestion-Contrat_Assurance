package DAO;

import models.Client;
import models.Conseiller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConseillerDAO {

    public void addConseiller(Conseiller conseiller) throws Exception {
        String sql = "INSERT INTO conseiller (id, nom, prenom, email) VALUES (?, ?, ?, ?)";

        Connection con = Database.getConnection();
        try (PreparedStatement p = con.prepareStatement(sql);) {
            p.setString(1, conseiller.getId());
            p.setString(2, conseiller.getNom());
            p.setString(3, conseiller.getPrenom());
            p.setString(4, conseiller.getEmail());

            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteConseiller(String id) throws Exception {
        String sql = "DELETE FROM conseiller WHERE id = ?";

        Connection con = Database.getConnection();
        try (PreparedStatement p = con.prepareStatement(sql)) {
            p.setString(1, id);

            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Conseiller> getAll() throws Exception {
        String sql = "SELECT * FROM conseiller";
        List<Conseiller> conseillers = new ArrayList<>();

        Connection con = Database.getConnection();
        try (PreparedStatement p = con.prepareStatement(sql);
             ResultSet rs = p.executeQuery();) {
            while (rs.next()) {
                Conseiller conseiller = new Conseiller();
                conseiller.setNom(rs.getString("nom"));
                conseiller.setPrenom(rs.getString("prenom"));
                conseiller.setEmail(rs.getString("email"));

                conseillers.add(conseiller);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conseillers;
    }

}


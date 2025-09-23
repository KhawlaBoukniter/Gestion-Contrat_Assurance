package DAO;

import models.Client;
import models.Conseiller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConseillerDAO {
    static Connection con = Database.getConnection();

    public void addConseiller(Conseiller conseiller) throws Exception {
        String sql = "INSERT INTO conseiller (id, nom, prenom, email) VALUES (?, ?, ?, ?)";

        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, conseiller.getId());
        p.setString(2, conseiller.getNom());
        p.setString(3, conseiller.getPrenom());
        p.setString(4, conseiller.getEmail());

        p.executeUpdate();
    }

    public void deleteConseiller(String id) throws Exception {
        String sql = "DELETE FROM conseiller WHERE id = ?";

        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, id);

        p.executeUpdate();
    }

    public List<Conseiller> getAll() throws Exception {
        String sql = "SELECT * FROM conseiller";

        PreparedStatement p = con.prepareStatement(sql);
        ResultSet rs = p.executeQuery();
        List<Conseiller> conseillers = new ArrayList();

        while (rs.next()) {
            Conseiller conseiller = new Conseiller();
            conseiller.setId(rs.getString("id"));
            conseiller.setNom(rs.getString("nom"));
            conseiller.setPrenom(rs.getString("prenom"));
            conseiller.setEmail(rs.getString("email"));

            conseillers.add(conseiller);
        }
        return conseillers;
    }

}


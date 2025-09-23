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


}


package DAO;

import models.Sinistre;
import models.TypeSinistre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SinistreDAO {
    static Connection con = Database.getConnection();

    public void addSinistre(Sinistre sinistre) throws Exception {
        String sql = "INSERT INTO sinistres (id, type_sinistre, date, cout, description, contrat_id) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, sinistre.getId());
        p.setObject(2, sinistre.getTypeSinistre().name());
        p.setObject(3, Timestamp.valueOf(sinistre.getDate()));
        p.setDouble(4, sinistre.getCout());
        p.setString(5, sinistre.getDescription());
        p.setObject(6, sinistre.getContrat());

        p.executeUpdate();
    }

    public void deleteSinistre(String id) throws Exception {
        String sql = "DELETE FROM sinistres WHERE id = ?";

        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, id);
        p.executeQuery();
    }

    public List<Sinistre> getAll() throws Exception {
        String sql = "SELECT * FROM sinistres";

        PreparedStatement p = con.prepareStatement(sql);
        ResultSet rs = p.executeQuery();
        List<Sinistre> sinistres = new ArrayList();

        while (rs.next()) {
            Sinistre sinistre = new Sinistre();

            sinistre.setId(rs.getString("id"));
            sinistre.setTypeSinistre(TypeSinistre.valueOf(rs.getString("type_sinistre")));
            sinistre.setDate(rs.getTimestamp("date").toLocalDateTime());
            sinistre.setDescription(rs.getString("description"));
            sinistre.setCout(rs.getDouble("cout"));
            sinistre.setContrat(rs.getString("contrat_id"));

            sinistres.add(sinistre);
        }
        return sinistres;
    }


}

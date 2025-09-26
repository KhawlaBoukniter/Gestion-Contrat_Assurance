package DAO;

import models.Sinistre;
import enums.TypeSinistre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SinistreDAO {

    public Boolean addSinistre(Sinistre sinistre) throws Exception {
        String sql = "INSERT INTO sinistres (id, type_sinistre, date, cout, description, contrat_id) VALUES (?, ?, ?, ?, ?, ?)";

        Connection con = Database.getConnection();
        try (PreparedStatement p = con.prepareStatement(sql);) {
            p.setString(1, sinistre.getId());
            p.setObject(2, sinistre.getTypeSinistre().name());
            p.setObject(3, Timestamp.valueOf(sinistre.getDate()));
            p.setDouble(4, sinistre.getCout());
            p.setString(5, sinistre.getDescription());
            p.setObject(6, sinistre.getContrat());

            p.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean deleteSinistre(String id) throws Exception {
        String sql = "DELETE FROM sinistres WHERE id = ?";

        Connection con = Database.getConnection();
        try (PreparedStatement p = con.prepareStatement(sql);) {
            p.setString(1, id);
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Sinistre> getAll() throws Exception {
        String sql = "SELECT * FROM sinistres";
        List<Sinistre> sinistres = new ArrayList<>();

        Connection con = Database.getConnection();
        try (PreparedStatement p = con.prepareStatement(sql);
             ResultSet rs = p.executeQuery())
        {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sinistres;
    }
}

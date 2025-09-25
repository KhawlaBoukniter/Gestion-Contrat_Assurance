package DAO;

import models.Contrat;
import enums.TypeContrat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratDAO {

    public void addContrat(Contrat contrat) throws Exception {
        String sql = "INSERT INTO contrats (id, date_debut, date_fin, type_contrat, client_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Database.getConnection(); PreparedStatement p = con.prepareStatement(sql);){
            p.setString(1, contrat.getId());
            p.setObject(2, Timestamp.valueOf(contrat.getDateDebut()));
            p.setObject(3, Timestamp.valueOf(contrat.getDateFin()));
            p.setObject(4, contrat.getTypeContrat().name());
            p.setObject(5, contrat.getClient());

            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContrat(String id) throws Exception {
        String sql = "DELETE FROM contrats WHERE id = ?";

        try (Connection con = Database.getConnection(); PreparedStatement p = con.prepareStatement(sql);){
            p.setString(1, id);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Contrat> getAll() throws Exception {
        String sql = "SELECT * FROM contrats";
        List<Contrat> contrats = new ArrayList();

        Connection con = Database.getConnection();
        try (PreparedStatement p = con.prepareStatement(sql);
             ResultSet rs = p.executeQuery()) {
            while (rs.next()) {
                Contrat contrat = new Contrat();
                contrat.setTypeContrat(TypeContrat.valueOf(rs.getString("type_contrat")));
                contrat.setDateDebut(rs.getTimestamp("date_debut").toLocalDateTime());
                contrat.setDateFin(rs.getTimestamp("date_fin").toLocalDateTime());
                contrat.setClient(rs.getString("client_id"));

                contrats.add(contrat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contrats;
    }

    public String getClientIdByContractId(String contractId) throws Exception {
        String sql = "SELECT client_id FROM contrats WHERE id = ?";

        Connection con = Database.getConnection();
        try (PreparedStatement p = con.prepareStatement(sql);
             ResultSet rs = p.executeQuery();){
            p.setString(1, contractId);

            if (rs.next()) {
                return rs.getString("client_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

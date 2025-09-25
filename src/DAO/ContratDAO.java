package DAO;

import models.Contrat;
import enums.TypeContrat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratDAO {

    public Boolean addContrat(Contrat contrat) throws Exception {
        String sql = "INSERT INTO contrats (id, date_debut, date_fin, type_contrat, client_id) VALUES (?, ?, ?, ?, ?)";

        Connection con = Database.getConnection();
        try (PreparedStatement p = con.prepareStatement(sql);){
            p.setString(1, contrat.getId());
            p.setDate(2, java.sql.Date.valueOf(contrat.getDateDebut()));
            p.setDate(3, java.sql.Date.valueOf(contrat.getDateFin()));
            p.setObject(4, contrat.getTypeContrat().name());
            p.setObject(5, contrat.getClient());

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

    public Boolean deleteContrat(String id) throws Exception {
        String sql = "DELETE FROM contrats WHERE id = ?";

        Connection con = Database.getConnection();
        try (PreparedStatement p = con.prepareStatement(sql);){
            p.setString(1, id);
            int rows = p.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
                contrat.setId(rs.getString("id"));
                contrat.setTypeContrat(TypeContrat.valueOf(rs.getString("type_contrat")));
                contrat.setDateDebut(rs.getDate("date_debut").toLocalDate());
                contrat.setDateFin(rs.getDate("date_fin").toLocalDate());
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

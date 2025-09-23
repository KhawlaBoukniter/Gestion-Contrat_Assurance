package DAO;

import models.Conseiller;
import models.Contrat;
import models.Sinistre;
import models.TypeContrat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContratDAO {
    static Connection con = Database.getConnection();

    public void addContrat(Contrat contrat) throws Exception {
        String sql = "INSERT INTO contrats (id, date_debut, date_fin, type_contrat, client_id) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, contrat.getId());
        p.setObject(2, Timestamp.valueOf(contrat.getDateDebut()));
        p.setObject(3, Timestamp.valueOf(contrat.getDateFin()));
        p.setObject(4, contrat.getTypeContrat().name());
        p.setObject(5, contrat.getClient());

        p.executeUpdate();
    }

    public void deleteContrat(String id) throws Exception {
        String sql = "DELETE FROM contrats WHERE id = ?";

        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, id);
        p.executeUpdate();
    }

}

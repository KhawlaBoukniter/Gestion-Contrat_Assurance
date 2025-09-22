package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public enum TypeContrat {
    AUTOMOBILE,
    IMMOBILIER,
    MALADIE
}

public class Contrat {
    private String id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private TypeContrat typeContrat;
    private String client;

    public Contrat() {}

    public Contrat (LocalDateTime dateDebut, LocalDateTime dateFin, TypeContrat typeContrat, String client) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeContrat = typeContrat;
        this.client = client;
    }

    public String getId() { return id; }
    public void setId( String id) { this.id = id; }

    public LocalDateTime getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }

    public LocalDateTime getDateFin() { return dateFin; }
    public void setDateFin(LocalDateTime dateFin) { this.dateFin = dateFin; }

    public TypeContrat getTypeContrat() { return typeContrat; }
    public void setTypeContrat(TypeContrat typeContrat) { this.typeContrat = typeContrat; }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public String toString() {
        return "Contrat: ID: " + id + " | Type: " + typeContrat + " | Date de debut: " + dateDebut + " | Date de fin: " + dateFin + " | Client: " + client;
    }

}

package models;

import enums.TypeContrat;

import java.time.LocalDate;
import java.util.UUID;

public class Contrat {
    private String id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private TypeContrat typeContrat;
    private String client;

    public Contrat() {}

    public Contrat (LocalDate dateDebut, LocalDate dateFin, TypeContrat typeContrat, String client) {
        this.id = UUID.randomUUID().toString().split("-")[0];
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeContrat = typeContrat;
        this.client = client;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public TypeContrat getTypeContrat() { return typeContrat; }
    public void setTypeContrat(TypeContrat typeContrat) { this.typeContrat = typeContrat; }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public String toString() {
        return "Contrat: ID: " + id + " | Type: " + typeContrat + " | Date de debut: " + dateDebut + " | Date de fin: " + dateFin + " | Client: " + client;
    }

}

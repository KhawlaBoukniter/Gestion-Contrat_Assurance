package models;

import java.time.LocalDateTime;
import java.util.UUID;

public enum TypeSinistre {
    ACCIDENT_VOITURE,
    ACCIDENT_MAISON,
    MALADIE
}

public class Sinistre {
    private String id;
    private LocalDateTime date;
    private String description;
    private Double cout;
    private TypeSinistre typeSinistre;
    private String contrat;

    public Sinistre() {}

    public Sinistre(LocalDateTime date, Double cout, String description, TypeSinistre typeSinistre, String contrat) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.description = description;
        this.cout = cout;
        this.typeSinistre = typeSinistre;
        this.contrat = contrat;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getCout() { return cout; }
    public void setCout(Double cout) { this.cout = cout; }

    public TypeSinistre getTypeSinistre() { return typeSinistre; }
    public void setTypeSinistre(TypeSinistre typeSinistre) { this.typeSinistre = typeSinistre; }

    public String getContrat() {
        return contrat;
    }
    public void setContrat(String contrat) { this.contrat = contrat; }

    public String toString() {
        return "Sinistre: ID: " + id + " | Type: " + typeSinistre + " | Date: " + date + " | Cout: " + cout + " | Contrat: " + contrat + " | Description: " + description;
    }
}

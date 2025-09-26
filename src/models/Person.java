package models;

import java.util.UUID;

public class Person {
    private String id;
    private String nom;
    private String prenom;
    private String email;

    public Person() {}

    public Person (String nom, String prenom, String email) {
        this.id = UUID.randomUUID().toString();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom;}

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String toString() {
        return "Nom: " + nom + " | Prenom: " + prenom + " | Email: " + email;
    }
}
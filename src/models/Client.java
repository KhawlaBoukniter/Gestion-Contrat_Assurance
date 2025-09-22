package models;

import java.util.HashMap;

public class Client extends Person {
    private String conseiller;

    public Client() {
        super();
    }

    public Client(String nom, String prenom, String email, String conseiller) {
        super(nom, prenom, email);
        this.conseiller = conseiller;
    }

    public String getConseiller() {
        return conseiller;
    }

    public void setConseiller(String conseiller) {
        this.conseiller = conseiller;
    }

    public String toString() {
        return super.toString() + " | CONSEILLER: " + getConseiller();
    }
}
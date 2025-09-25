package views;

import models.Contrat;
import models.TypeContrat;
import services.ContratService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

public class ContratView {

    private ContratService contratService = new ContratService();
    private Scanner sc = new Scanner(System.in);

    public void menuContrat() throws Exception {
        int choix;
        do {
            System.out.println("\nGérer les Contrats");
            System.out.println("1. Ajouter un contrat");
            System.out.println("2. Supprimer un contrat");
            System.out.println("3. Afficher les informations d'un contrat par ID");
            System.out.println("4. Afficher les contrats souscrits d'un client par ID");
            System.out.println("0. Quitter");

            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    ajouterContrat();
                    break;
                case 2:
                    supprimerContrat();
                    break;
                case 3:
                    AfficherContratById();
                    break;
                case 4:
                    AfficherContratsParClient();
                    break;
                case 0:
                    System.out.println("Retour au menu precedent...");
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        } while (choix != 0);
    }

}

package views;

import models.Client;
import models.Conseiller;
import services.ClientService;
import services.ConseillerService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConseillerView {

    private ClientService clientService = new ClientService();
    private ConseillerService conseillerService = new ConseillerService();
    private Scanner sc = new Scanner(System.in);

    public void menuConseiller() throws Exception {
        int choix;
        do {
            System.out.println("\nGérer les Conseillers");
            System.out.println("1. Ajouter un conseiller");
            System.out.println("2. Supprimer un conseiller");
            System.out.println("3. Rechercher un conseiller par ID");
            System.out.println("4. Afficher clients d'un conseiller par ID");
            System.out.println("0. Retour au menu précédent");
            System.out.print("Votre choix : ");

            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    ajouterConseiller();
                    break;
                case 2:
                    supprimerConseiller();
                    break;
                case 3:
                    rechercherConseillerParId();
                    break;
                case 4:
                    afficherClientsParConseiller();
                    break;
                case 0:
                    System.out.println("Retour au menu precedent...");
                    break;
                default:
                    System.out.println("Choix invalide");
            }

        } while (choix != 0);
    }


}

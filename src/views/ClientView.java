package views;

import models.Client;
import models.Conseiller;
import services.ClientService;
import services.ConseillerService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientView {

    private ClientService clientService = new ClientService();
    private ConseillerService conseillerService = new ConseillerService();
    private Scanner sc = new Scanner(System.in);

    public void menuClient() throws Exception {
        int choix;
        do {
            System.out.println("\nGérer les Clients");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Supprimer un client");
            System.out.println("3. Rechercher un client par nom");
            System.out.println("4. Rechercher un client par ID");
            System.out.println("5. Afficher clients d'un conseiller par ID");
            System.out.println("0. Retour au menu précédent");
            System.out.print("Votre choix : ");

            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    ajouterClient();
                    break;
                case 2:
                    supprimerClient();
                    break;
                case 3:
                    rechercherClientParId();
                    break;
                case 4:
                    rechercherClientParNom();
                    break;
                case 5:
                    afficherClientsParConseiller();
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

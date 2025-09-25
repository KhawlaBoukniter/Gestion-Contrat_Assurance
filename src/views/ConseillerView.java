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

    private void ajouterConseiller() throws Exception {
        System.out.print("Nom: ");
        String nom = sc.nextLine();
        System.out.print("Prénom: ");
        String prenom = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        Conseiller conseiller = new Conseiller(nom, prenom, email);
        conseillerService.addConseiller(conseiller);
        System.out.println("Conseiller ajouté avec ID : " + conseiller.getId());
    }

    private void supprimerConseiller() throws Exception {
        System.out.print("ID du conseiller à supprimer : ");
        String id = sc.nextLine();
        conseillerService.deleteById(id);
        System.out.println("Conseiller supprimé si existant.");
    }

    private void rechercherConseillerParId() throws Exception {
        System.out.print("ID du conseiller : ");
        String id = sc.nextLine();
        Optional<Conseiller> conseillerOpt = conseillerService.getById(id);
        conseillerOpt.ifPresent(
                c -> System.out.println("Conseiller trouvé : " + c.getNom() + " " + c.getPrenom()));
    }

    private void afficherClientsParConseiller() throws Exception {
        System.out.print("ID du conseiller : ");
        String idConseiller = sc.nextLine();

        // Conseiller conseiller = conseillerService.getById(idConseiller).orElse(null);

        clientService.getByConseiller(idConseiller);

    }

}

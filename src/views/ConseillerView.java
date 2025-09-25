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

    public ConseillerView(ConseillerService conseillerService) {
        this.conseillerService = conseillerService;
    }

    public ConseillerView() {}

    public void menuConseiller() throws Exception {
        int choix = -1;
        do {
            System.out.println("\nGérer les Conseillers");
            System.out.println("1. Ajouter un conseiller");
            System.out.println("2. Supprimer un conseiller");
            System.out.println("3. Rechercher un conseiller par ID");
            System.out.println("4. Afficher clients d'un conseiller par ID");
            System.out.println("0. Retour au menu précédent");
            System.out.print("Votre choix : ");

            if (sc.hasNextInt()) {
                choix = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Choix invalide. Veuillez saisir un nombre.");
                sc.nextLine();
                continue;
            }

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

        if (conseillerService.addConseiller(conseiller)) {
            System.out.println("Conseiller ajouté avec ID : " + conseiller.getId());
        } else {
            System.out.println("Conseiller déjà trouvé avec cet email");
        }

    }

    private void supprimerConseiller() throws Exception {
        System.out.print("ID du conseiller à supprimer : ");
        String id = sc.nextLine();

        if (conseillerService.deleteById(id)) {
            System.out.println("Conseiller supprimé avec succès.");
        } else {
            System.out.println("Aucun conseiller trouvé avec cet ID.");
        }
    }

    private void rechercherConseillerParId() throws Exception {
        System.out.print("ID du conseiller : ");
        String id = sc.nextLine();

        Optional<Conseiller> conseillerOpt = conseillerService.getById(id);

        if (conseillerOpt.isPresent()) {
            Conseiller c = conseillerOpt.get();
            System.out.println("Conseiller trouvé : " + c.getNom() + " " + c.getPrenom() + " (" + c.getEmail() + ")");
        } else {
            System.out.println("Aucun conseiller trouvé avec cet ID.");
        }
    }

    private void afficherClientsParConseiller() throws Exception {
        System.out.print("ID du conseiller : ");
        String idConseiller = sc.nextLine();

        List<Client> clients = clientService.getByConseiller(idConseiller);

        if (clients.isEmpty()) {
            System.out.println("Aucun client associé à ce conseiller.");
        } else {
            System.out.println("Liste des clients :");
            clients.forEach(c -> System.out.println("- " + c.getNom() + " " + c.getPrenom()));
        }
    }

}

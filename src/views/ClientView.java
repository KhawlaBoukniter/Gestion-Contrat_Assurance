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

    public ClientView(ClientService clientService) {
        this.clientService = clientService;
    }

    public ClientView() {}

    public void menuClient() throws Exception {
        int choix = -1;
        do {
            System.out.println("\nGérer les Clients");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Supprimer un client");
            System.out.println("3. Rechercher un client par nom");
            System.out.println("4. Rechercher un client par ID");
            System.out.println("5. Afficher clients d'un conseiller par ID");
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
                    ajouterClient();
                    break;
                case 2:
                    supprimerClient();
                    break;
                case 3:
                    rechercherClientParNom();
                    break;
                case 4:
                    rechercherClientParId();
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

    private void ajouterClient() throws Exception {
        List<Conseiller> conseillers = conseillerService.getAll();

        if (conseillers.isEmpty()) {
            System.out.println("Aucun conseiller disponible");
            return;
        }

        System.out.println("Liste des conseillers(ID - Nom Prénom):");
        for (Conseiller c : conseillers) {
            System.out.println(c.getId() + " - " + c.getNom() + " " + c.getPrenom());
        }

        System.out.print("Nom: ");
        String nom = sc.nextLine();
        System.out.print("Prénom: ");
        String prenom = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Entrez l'ID du conseiller choisi: ");
        String conseillerId = sc.nextLine();

        try {
            clientService.addClient(nom, prenom, email, conseillerId);
            System.out.println("Client ajouté");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private void supprimerClient() throws Exception {
        System.out.print("ID du client à supprimer: ");
        String id = sc.nextLine();

        if (clientService.deleteById(id)) {
            System.out.println("Client supprimé");
        } else {
            System.out.println("Aucun client trouvé avec cet ID.");
        }

    }

    private void rechercherClientParId() throws Exception {
        System.out.print("ID du client : ");
        String id = sc.nextLine();
        Optional<Client> clientOpt = clientService.getById(id);

        if (clientOpt.isPresent()) {
            Client c = clientOpt.get();
            System.out.println("Client trouvé : " + c.getNom() + " " + c.getPrenom());
        } else {
            System.out.println("Aucun client trouvé avec cet ID.");
        }
    }

    private void rechercherClientParNom() throws Exception {
        System.out.print("Nom à rechercher : ");
        String nom = sc.nextLine();
        List<Client> clients = clientService.getByName(nom);
        if (clients.isEmpty()) {
            System.out.println("Aucun client trouvé");
        } else {
            clients.forEach(c -> System.out.println(c.getId() + " - " + c.getNom() + " " + c.getPrenom()));
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

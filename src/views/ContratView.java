package views;

import models.Client;
import models.Contrat;
import enums.TypeContrat;
import services.ContratService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ContratView {

    private ContratService contratService = new ContratService();
    private Scanner sc = new Scanner(System.in);

    public ContratView(ContratService contratService) {
        this.contratService = contratService;
    }

    public ContratView() {}

    public void menuContrat() throws Exception {
        int choix;
        do {
            System.out.println("\nGérer les Contrats");
            System.out.println("1. Ajouter un contrat");
            System.out.println("2. Supprimer un contrat");
            System.out.println("3. Afficher les informations d'un contrat par ID");
            System.out.println("4. Afficher les contrats souscrits d'un client par ID");
            System.out.println("0. Retour au menu precedent");
            System.out.print("Votre choix: ");

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

    public TypeContrat typeContrat() {
        int choix;

        System.out.println("\nChoisissez le type de contrat");
        System.out.println("1. Automobile");
        System.out.println("2. Immobilier");
        System.out.println("3. Maladie");
        System.out.print("Votre choix: ");

        choix = sc.nextInt();
        sc.nextLine();
        TypeContrat lastCoice = null;

        switch (choix) {
            case 1:
                lastCoice = TypeContrat.AUTOMOBILE;
                break;
            case 2:
                lastCoice = TypeContrat.IMMOBILIER;
                break;
            case 3:
                lastCoice = TypeContrat.MALADIE;
                break;
            default:
                System.out.println("Choix invalide");
                break;
        }
        return lastCoice;
    }

//    public void ajouterContrat() throws Exception {
//        TypeContrat typeContrat = typeContrat();
//        System.out.print("Date de debut (yyyy-MM-dd): ");
//        LocalDate dateDebut = LocalDate.parse(sc.nextLine());
//        System.out.print("Date de fin (yyyy-MM-dd): ");
//        LocalDate dateFin = LocalDate.parse(sc.nextLine());
//        System.out.print("ID du client: ");
//        String clientId = sc.nextLine();
//
//        Contrat contrat = new Contrat(dateDebut, dateFin, typeContrat, clientId);
//        contratService.addContract(contrat);
//        System.out.println("Contrat ajouté avec ID : " + contrat.getId());
//    }

    private void ajouterContrat() throws Exception {
        List<Client> clients = contratService.getAllClients();
        if (clients.isEmpty()) {
            System.out.println("Aucun client disponible");
            return;
        }

        System.out.println("Liste des clients (ID - Nom Prénom) :");
        for (Client c : clients) {
            System.out.println(c.getId() + " - " + c.getNom() + " " + c.getPrenom());
        }

        System.out.print("Entrez l'ID du client choisi : ");
        String clientId = sc.nextLine();

        TypeContrat typeContrat = typeContrat();
        System.out.print("Date de debut (yyyy-MM-dd): ");
        LocalDate dateDebut = LocalDate.parse(sc.nextLine());
        System.out.print("Date de fin (yyyy-MM-dd): ");
        LocalDate dateFin = LocalDate.parse(sc.nextLine());

        if (dateFin.isBefore(dateDebut)) {
            System.out.println("Erreur : la date de fin ne peut pas être avant la date de début !");
            return;
        }

        try {
            boolean success = contratService.addContractWithClientId(clientId, dateDebut, dateFin, typeContrat);
            if (success) {
                System.out.println("Contrat créé avec le client ID : " + clientId);
            } else {
                System.out.println("ID contrat déjà existant");
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void supprimerContrat() throws Exception {
        System.out.print("ID du contrat à supprimer: ");
        String id = sc.nextLine();
        if (contratService.deleteById(id)) {
            System.out.println("Contrat supprimé avec succès");
        }
    }

    public void AfficherContratById() throws Exception {
        System.out.print("ID du contrat: ");
        String id = sc.nextLine();
        Optional<Contrat> contratOpt = contratService.getById(id);
        contratOpt.ifPresent(c -> System.out.print("Type: " + c.getTypeContrat() + " | Date début: " + c.getDateDebut() + " | Date Fin: " + c.getDateFin() + " | ID du client: " + c.getClient()));

        if (contratOpt.isPresent()) {
            System.out.println(contratOpt.get());
        } else {
            System.out.println("Contrat non trouvé pour l'ID : " + id);
        }
    }

    public void AfficherContratsParClient() throws Exception {
        System.out.print("ID du client: ");
        String idClient = sc.nextLine();

        List<Contrat> contrats = contratService.getAllByClient(idClient);

        if (contrats.isEmpty()) {
            System.out.println("Aucun contrat trouvé pour ce client.");
        } else {
            contrats.forEach(System.out::println); // utilise le toString() de Contrat
        }
    }
}

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

    public TypeContrat typeContrat() {
        int choix;

        System.out.println("\nChoisissez le type de contrat");
        System.out.println("1. Automobile");
        System.out.println("2. Immobilier");
        System.out.println("3. Maladie");

        choix = sc.nextInt();
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

    public void ajouterContrat() throws Exception {
        TypeContrat typeContrat = typeContrat();
        System.out.print("Date de debut: ");
        LocalDateTime dateDebut = LocalDateTime.parse(sc.nextLine());
        System.out.print("Date de fin: ");
        LocalDateTime dateFin = LocalDateTime.parse(sc.nextLine());
        System.out.print("ID du client: ");
        String clientId = sc.nextLine();

        Contrat contrat = new Contrat(dateDebut, dateFin, typeContrat, clientId);
        contratService.addContract(contrat);
        System.out.println("Contrat ajouté avec ID : " + contrat.getId());
    }

    public void supprimerContrat() throws Exception {
        System.out.print("ID du contrat à supprimer: ");
        String id = sc.nextLine();
        contratService.deleteById(id);

    }

    private void AfficherContratById() throws Exception {
        System.out.print("ID du contrat: ");
        String id = sc.nextLine();
        Optional<Contrat> contratOpt = contratService.getById(id);
        contratOpt.ifPresent(c -> System.out.print("Type: " + c.getTypeContrat() + " | Date début: " + c.getDateDebut() + " | Date Fin: " + c.getDateFin() + " | ID du client: " + c.getClient()));
    }
}

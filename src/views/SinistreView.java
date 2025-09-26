package views;

import models.Sinistre;
import enums.TypeSinistre;
import services.SinistreService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SinistreView {
    private SinistreService sinistreService = new SinistreService();
    private Scanner sc = new Scanner(System.in);

    public SinistreView (SinistreService sinistreService) {
        this.sinistreService = sinistreService;
    }

    public SinistreView() {}

    public void menuSinistre() throws Exception {
        int choix;
        do {
            System.out.println("\nGerer les Sinistres");
            System.out.println("1. Ajouter un sinistre");
            System.out.println("2. Supprimer un sinistre");
            System.out.println("3. Rechercher un Sinistre par ID");
            System.out.println("4. Calculer les couts totaux des sinistres d’un client");
            System.out.println("5. Afficher les sinistres d’un contrat");
            System.out.println("6. Afficher les sinistres triés par montant decroissant");
            System.out.println("7. Afficher les sinistres par l’id d’un client");
            System.out.println("8. Afficher les sinistres qui se sont produits avant une date donnée");
            System.out.println("9. Afficher les sinistres dont le cout est superieur a un montant donné");
            System.out.println("0. Retour au menu précédent");

            System.out.print("Votre choix: ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    ajouterSinistre();
                    break;
                case 2:
                    supprimerSinistre();
                    break;
                case 3:
                    rechercherSinistre();
                    break;
                case 4:
                    calculerCoutTotal();
                    break;
                case 5:
                    afficherParContrat();
                    break;
                case 6:
                    afficherTriesParMontant();
                    break;
                case 7:
                    afficherParClient();
                    break;
                case 8:
                    afficherAvantDate();
                    break;
                case 9:
                    afficherCoutSuperieur();
                    break;
                case 0:
                    System.out.println("Retour...");
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        } while (choix != 0);
    }

    public TypeSinistre typeSinistre() {
        int choix;

        System.out.println("\nChoisissez le type du sinistre");
        System.out.println("1. Accident de voiture");
        System.out.println("2. Accident de maison");
        System.out.println("3. Maladie");
        System.out.print("Votre choix: ");

        choix = sc.nextInt();
        sc.nextLine();

        TypeSinistre type = null;

        switch (choix) {
            case 1:
                type = TypeSinistre.ACCIDENT_VOITURE;
                break;
            case 2:
                type = TypeSinistre.ACCIDENT_MAISON;
                break;
            case 3:
                type = TypeSinistre.MALADIE;
                break;
            default:
                System.out.println("Choix invalide");
                break;
        }
        return type;
    }

    public void ajouterSinistre() throws Exception {
        TypeSinistre type = typeSinistre();
        System.out.print("Description : ");
        String description = sc.nextLine();
        System.out.print("Cout : ");
        Double cout = sc.nextDouble();
        sc.nextLine();

        System.out.print("ID du contrat associé : ");
        String contratId = sc.nextLine();

        Sinistre s = new Sinistre(LocalDateTime.now(), cout, description, type, contratId);
        try {
            Boolean success = sinistreService.addSinistre(s);
            if (success) {
                System.out.println("Sinistre ajouté avec succès : " + s);
            } else {
                System.out.println("Erreur : impossible d'ajouter le sinistre");
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private void supprimerSinistre() throws Exception {
        System.out.print("ID du sinistre à supprimer : ");
        String id = sc.nextLine();
        Boolean succes = sinistreService.deleteById(id);
        if (succes) {
            System.out.println("Sinistre supprimé avec succès");
        } else {
            System.out.println("Sinistre introuvable");
        }
    }

    private void rechercherSinistre() throws Exception {
        System.out.print("ID du sinistre : ");
        String id = sc.nextLine();
        Optional<Sinistre> sinistre = sinistreService.getById(id);

        if (sinistre.isPresent()) {
            System.out.println("Trouvé : " + sinistre.get());
        } else {
            System.out.println("Sinistre non trouvé");
        }
    }

    private void afficherParContrat() throws Exception {
        System.out.print("ID du contrat : ");
        String contratId = sc.nextLine();

        List<Sinistre> list = sinistreService.getByContrat(contratId);
        if(list.isEmpty()){
            System.out.println("Aucun sinistre trouvé pour ce contrat.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void afficherParClient() throws Exception {
        System.out.print("ID du client : ");
        String clientId = sc.nextLine();

        List<Sinistre> list = sinistreService.getSinistresByClientId(clientId);
        if(list.isEmpty()) {
            System.out.println("Aucun sinistre trouvé pour ce client.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void afficherAvantDate() throws Exception {
        System.out.print("Date limite (yyyy-MM-ddTHH:mm) : ");
        LocalDateTime date = LocalDateTime.parse(sc.nextLine());

        List<Sinistre> list = sinistreService.getBeforeDate(date);
        if(list.isEmpty()) {
            System.out.println("Aucun sinistre avant cette date.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void afficherCoutSuperieur() throws Exception {
        System.out.print("Montant minimum : ");
        Double montant = sc.nextDouble();
        sc.nextLine();

        List<Sinistre> list = sinistreService.getByCoutGreaterThan(montant);
        if(list.isEmpty()) {
            System.out.println("Aucun sinistre supérieur à ce montant.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void afficherTriesParMontant() throws Exception {
        List<Sinistre> list = sinistreService.getSortedByCoutDesc();
        if(list.isEmpty()) {
            System.out.println("Aucun sinistre trouvé.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void calculerCoutTotal() throws Exception {
        System.out.print("ID du client : ");
        String clientId = sc.nextLine();
        Double total = sinistreService.calculateTotalCostByClientId(clientId);
        System.out.println("Cout total = " + total);
    }
}

package views;

import services.*;

import java.util.Scanner;

public class MainMenu {
    private final Scanner sc = new Scanner(System.in);

    private final ConseillerView conseillerView;
    private final ClientView clientView;
    private final ContratView contratView;
    private final SinistreView sinistreView;

    public MainMenu(ConseillerService conseillerService,
                    ClientService clientService,
                    ContratService contratService,
                    SinistreService sinistreService) {
        this.conseillerView = new ConseillerView(conseillerService);
        this.clientView = new ClientView(clientService);
        this.contratView = new ContratView(contratService);
        this.sinistreView = new SinistreView(sinistreService);
    }

    public void afficherMenu() throws Exception {
        int choix;
        do {
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("1. Gestion des conseillers");
            System.out.println("2. Gestion des clients");
            System.out.println("3. Gestion des contrats");
            System.out.println("4. Gestion des sinistres");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    conseillerView.menuConseiller();
                    break;
                case 2:
                    clientView.menuClient();
                    break;
                case 3:
                    contratView.menuContrat();
                    break;
                case 4:
                    sinistreView.menuSinistre();
                    break;
                case 0:
                    System.out.println("Merci d’avoir utilisé l’application");
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        } while (choix != 0);
    }
}

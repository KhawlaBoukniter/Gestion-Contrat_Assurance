import views.ClientView;
import views.ContratView;
import views.SinistreView;
import views.ConseillerView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        ClientView clientView = new ClientView();
        ContratView contratView = new ContratView();
        SinistreView sinistreView = new SinistreView();
        ConseillerView conseillerView = new ConseillerView();

        int choix;
        do {
            System.out.println("\nMenu Principal");
            System.out.println("1. Gérer les conseillers");
            System.out.println("2. Gérer les clients");
            System.out.println("3. Gérer les contrats");
            System.out.println("4. Gérer les sinistres");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
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
                    System.out.println("Sortie de l'application...");
                    break;
                default:
                    System.out.println("Choix invalide, réessayez.");
                    break;
            }

        } while (choix != 0);

        sc.close();
    }
}

import DAO.ClientDAO;
import DAO.ConseillerDAO;
import DAO.ContratDAO;
import DAO.SinistreDAO;
import services.ClientService;
import services.ConseillerService;
import services.ContratService;
import services.SinistreService;
import views.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            ConseillerDAO conseillerDAO = new ConseillerDAO();
            ClientDAO clientDAO = new ClientDAO();
            ContratDAO contratDAO = new ContratDAO();
            SinistreDAO sinistreDAO = new SinistreDAO();

            ConseillerService conseillerService = new ConseillerService(conseillerDAO);
            ClientService clientService = new ClientService(clientDAO);
            ContratService contratService = new ContratService(contratDAO);
            SinistreService sinistreService = new SinistreService(sinistreDAO);

            MainMenu menu = new MainMenu(
                    conseillerService,
                    clientService,
                    contratService,
                    sinistreService
            );
            menu.afficherMenu();

        } catch (Exception e) {
            System.err.println("Erreur lors du démarrage de l’application : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

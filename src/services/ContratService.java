package services;

import DAO.ClientDAO;
import DAO.ContratDAO;
import enums.TypeContrat;
import models.Client;
import models.Contrat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ContratService {
    private ContratDAO contratDAO = new ContratDAO();
    private ClientDAO clientDAO = new ClientDAO();
    Scanner scanner = new Scanner(System.in);

    public ContratService () {}

    public ContratService (ContratDAO contratDAO) {
        this.contratDAO = contratDAO;
    }

    public ContratService (ContratDAO contratDAO, ClientDAO clientDAO) {
        this.contratDAO = contratDAO;
        this.clientDAO = clientDAO;
    }

//    public void addContract(Contrat contrat) throws Exception {
//        if (contrat.getDateDebut()
//                .isAfter(contrat.getDateFin())) {
//            throw new IllegalArgumentException("La date de début doit être avant la date de fin.");
//        }
//        contratDAO.addContrat(contrat);
//    }

    public Boolean deleteById(String id) throws Exception {
        return contratDAO.deleteContrat(id);
    }

    public Optional<Contrat> getById(String id) throws Exception {
        List<Contrat> contrats = contratDAO.getAll();

        Optional<Contrat> contrat = contrats.stream()
                .filter(c -> id.equals(c.getId()))
                .findFirst();
        return contrat;
    }

    public List<Contrat> getAll() throws Exception {
        return contratDAO.getAll();
    }

    public List<Contrat> getAllByClient(String id) throws Exception {
        List<Contrat> filteredContrats = contratDAO.getAll().stream()
                .filter(c -> id.equals(c.getClient()))
                .collect(Collectors.toList());

        return filteredContrats;
    }

    public Boolean addContractWithClientId(String clientId, LocalDate dateDebut, LocalDate dateFin, TypeContrat typeContrat) throws Exception {
        List<Client> clients = clientDAO.getAll();

        boolean existe = clients.stream().anyMatch(c -> clientId.equals(c.getId()));
        if (!existe) throw new Exception("Client inconnu");

        if (dateFin.isBefore(dateDebut)) {
            System.out.println("Erreur : la date de fin ne peut pas être avant la date de début !");
            return false;
        }

        Contrat contrat = new Contrat(dateDebut, dateFin, typeContrat, clientId);
        return contratDAO.addContrat(contrat);
    }

    public List<Client> getAllClients() throws Exception {
        return clientDAO.getAll();
    }

    public void creerContratAvecChoixClient() throws Exception {
        List<Client> clients = clientDAO.getAll();
        if (clients.isEmpty()) {
            System.out.println("Aucun client trouvé.");
            return;
        }
        System.out.println("Liste des clients (ID - Nom Prenom):");
        for (Client c : clients) {
            System.out.println(c.getId() + " - " + c.getNom() + " " + c.getPrenom());
        }

        System.out.print("Entrez l'ID du client choisi: ");
        String clientIdChoisi = scanner.nextLine();

        boolean existe = clients.stream().anyMatch(c -> clientIdChoisi.equals(c.getId()));
        if (!existe) {
            System.out.println("Client inconnu. Opération annulée.");
            return;
        }

        Contrat contrat = new Contrat();
//         client.setId();
//         client.setNom();
//         client.setPrenom();
//         client.setEmail();
        contrat.setClient(clientIdChoisi);

        contratDAO.addContrat(contrat);
        System.out.println("Contrat créé avec le client ID: " + clientIdChoisi);
    }

}
package services;

import DAO.ClientDAO;
import DAO.ConseillerDAO;
import models.Client;
import models.Conseiller;
import models.Sinistre;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClientService {
    private ClientDAO clientDAO = new ClientDAO();
    private ConseillerDAO conseillerDAO = new ConseillerDAO();
    Scanner scanner = new Scanner(System.in);

    public ClientService () {}

    public ClientService (ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public ClientService(ClientDAO clientDAO, ConseillerDAO conseillerDAO) {
        this.clientDAO = clientDAO;
        this.conseillerDAO = conseillerDAO;
    }

    public void addClient(String nom, String prenom, String email, String conseillerId) throws Exception {
        List<Conseiller> conseillers = conseillerDAO.getAll();
        boolean idValide = conseillers.stream().anyMatch(c -> conseillerId.equals(c.getId()));
        if (!idValide) {
            throw new Exception("ID de conseiller invalide");
        }

        Client client = new Client(nom, prenom, email, conseillerId);

        if (!clientDAO.addClient(client)) {
            throw new Exception("Client déjà trouvé avec cet email");
        }
    }

    public Boolean deleteById(String id) throws Exception {
        return clientDAO.deleteClient(id);
    }

    public Optional<Client> getById(String id) throws Exception {
        List<Client> clients = clientDAO.getAll();
        Optional<Client> client = clients.stream()
                .filter(c -> id.equals(c.getId()))
                .findFirst();

        return client;
    }

    public List<Client> getAll() throws Exception {
        return clientDAO.getAll();
    }

    public List<Client> getByName(String nom) throws Exception {
        List<Client> clients = clientDAO.getAll().stream()
                .filter(c -> c.getNom() != null && nom.equalsIgnoreCase(c.getNom())).sorted()
                .collect(Collectors.toList());

        return clients;
    }

    public List<Client> getByConseiller(String id) throws Exception {
        List<Client> filteredclients = clientDAO.getAll().stream()
                .filter(s -> id.equals(s.getConseiller()))
                .collect(Collectors.toList());

        return filteredclients;
    }

}

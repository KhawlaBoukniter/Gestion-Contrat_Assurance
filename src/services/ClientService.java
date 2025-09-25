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

    public Boolean addClient(Client client) throws Exception {
        return clientDAO.addClient(client);
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

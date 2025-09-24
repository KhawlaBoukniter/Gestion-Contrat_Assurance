package services;

import DAO.ClientDAO;
import models.Client;
import models.Sinistre;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientService {
    private ClientDAO clientDAO = new ClientDAO();

    public void addClient(Client client) throws Exception {
        clientDAO.addClient(client);
    }

    public Boolean deleteById(String id) throws Exception {
        List<Client> clients = clientDAO.getAll();

        return clients.removeIf(c -> id.equals(c.getId()));
    }

}

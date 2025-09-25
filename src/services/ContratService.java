package services;

import DAO.ClientDAO;
import DAO.ContratDAO;
import models.Client;
import models.Contrat;

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

    public void addContract(Contrat contrat) throws Exception {
        if (contrat.getDateDebut()
                .isAfter(contrat.getDateFin())) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin.");
        }
        contratDAO.addContrat(contrat);
    }

    public void deleteById(String id) throws Exception {
        contratDAO.deleteContrat(id);
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


}
package services;

import DAO.ClientDAO;
import DAO.ContratDAO;
import models.Client;
import models.Contrat;
import models.TypeContrat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ContratService {
    private ContratDAO contratDAO = new ContratDAO();
    private ClientDAO clientDAO = new ClientDAO();

    public void addContract(Contrat contrat) throws Exception {
        if (contrat.getDateDebut()
                .isAfter(contrat.getDateFin())) {
            throw new Exception("La date de début doit être avant la date de fin.");
        }
        contratDAO.addContrat(contrat);
    }

    public Boolean deleteById(String id) throws Exception {
        List<Contrat> contrats = contratDAO.getAll();
        return contrats.removeIf(c -> id.equals(c.getId()));
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
        List<Contrat> contrats = contratDAO.getAll();
        List<Contrat> filteredContrats = contrats.stream()
                .filter(c -> id.equals(c.getClient()))
                .collect(Collectors.toList());

        return filteredContrats;
    }
}
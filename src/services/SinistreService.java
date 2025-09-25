package services;

import DAO.ContratDAO;
import DAO.Database;
import DAO.SinistreDAO;
import models.Client;
import models.Contrat;
import models.Sinistre;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SinistreService {
    private SinistreDAO sinistreDAO = new SinistreDAO();
    private ContratDAO contratDAO = new ContratDAO();
    Scanner scanner = new Scanner(System.in);

    public SinistreService(SinistreDAO sinistreDAO, ContratDAO contratDAO) {
        this.sinistreDAO = sinistreDAO;
        this.contratDAO = contratDAO;
    }

    public SinistreService() {}

    public SinistreService(SinistreDAO sinistreDAO) {
        this.sinistreDAO = sinistreDAO;
    }

    public void addSinistre(Sinistre sinistre) throws Exception {
        sinistreDAO.addSinistre(sinistre);
    }

    public void deleteById(String id) throws Exception {
        sinistreDAO.deleteSinistre(id);
    }

    public Optional<Sinistre> getById(String id) throws Exception {
        List<Sinistre> sinistres = sinistreDAO.getAll();

        return sinistres.stream()
                .filter(s -> id.equals(s.getId()))
                .findFirst();
    }

    public List<Sinistre> getByContrat(String id) throws Exception {
        List<Sinistre> filteredsinistres = sinistreDAO.getAll().stream()
                .filter(s -> id.equals(s.getContrat()))
                .collect(Collectors.toList());

        return filteredsinistres;
    }

    public List<Sinistre> getAll() throws Exception {
        return sinistreDAO.getAll();
    }

    public List<Sinistre> getAllByContrat(String id) throws Exception {
        List<Sinistre> sinistres = sinistreDAO.getAll();
        return sinistres.stream()
                .filter(s -> id.equals(s.getContrat()))
                .collect(Collectors.toList());
    }

    public List<Sinistre> getSortedByCoutDesc() throws Exception {
        List<Sinistre> sinistres = sinistreDAO.getAll();
        return sinistres.stream()
                .sorted(Comparator.comparingDouble(Sinistre::getCout).reversed())
                .collect(Collectors.toList());
    }

    public List<Sinistre> getBeforeDate(LocalDateTime date) throws Exception {
        List<Sinistre> sinistres = sinistreDAO.getAll();
        return sinistres.stream()
                .filter(s -> s.getDate().isBefore(date))
                .collect(Collectors.toList());
    }

    public List<Sinistre> getByCoutGreaterThan(Double cout) throws Exception {
        List<Sinistre> sinistres = sinistreDAO.getAll();
        return sinistres.stream()
                .filter(s -> cout < s.getCout())
                .collect(Collectors.toList());
    }

    public List<Sinistre> getSinistresByClientId(String clientId) throws Exception {
        List<Sinistre> allSinistres = sinistreDAO.getAll();

        return allSinistres.stream()
                .filter(s -> {
                    String contratId = s.getContrat();
                    if (contratId == null) return false;
                    try {
                        String cidFromContract = contratDAO.getClientIdByContractId(contratId);
                        return clientId != null && clientId.equals(cidFromContract);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    public double calculateTotalCostByClientId(String clientId) throws Exception {
        return getSinistresByClientId(clientId).stream()
                .map(Sinistre::getCout)
                .filter(c -> c != null)
                .mapToDouble(Double::doubleValue)
                .sum();
    }


}


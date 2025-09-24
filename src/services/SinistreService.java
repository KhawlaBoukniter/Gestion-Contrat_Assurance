package services;

import DAO.Database;
import DAO.SinistreDAO;
import models.Contrat;
import models.Sinistre;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SinistreService {
    private SinistreDAO sinistreDAO = new SinistreDAO();

    public void addClient(Sinistre sinistre) throws Exception {
        sinistreDAO.addSinistre(sinistre);
    }

    public Boolean deleteById(String id) throws Exception {
        List<Sinistre> sinistres = sinistreDAO.getAll();
        return sinistres.removeIf(c -> id.equals(c.getId()));
    }

    public Optional<Sinistre> getById(String id) throws Exception {
        List<Sinistre> sinistres = sinistreDAO.getAll();

        Optional<Sinistre> sinistre = sinistres.stream()
                .filter(s -> id.equals(s.getId()))
                .findFirst();
        return sinistre;
    }

    public List<Sinistre> getAll() throws Exception {
        return sinistreDAO.getAll();
    }

    public List<Sinistre> getAllByContrat(String id) throws Exception {
        List<Sinistre> sinistres = sinistreDAO.getAll();
        List<Sinistre> filteredsinistres = sinistres.stream()
                .filter(s -> id.equals(s.getContrat()))
                .collect(Collectors.toList());

        return filteredsinistres;
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

}


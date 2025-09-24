package services;

import DAO.ConseillerDAO;
import models.Conseiller;

import java.util.List;
import java.util.Optional;

public class ConseillerService {
    private ConseillerDAO conseillerDAO = new ConseillerDAO();

    public void addConseiller(Conseiller conseiller) throws Exception {
        conseillerDAO.addConseiller(conseiller);
    }

    public Boolean deleteById(String id) throws Exception {
        List<Conseiller> conseillers = conseillerDAO.getAll();
        return conseillers.removeIf(c -> id.equals(c.getId()));
    }

    public Optional<Conseiller> getById(String id) throws Exception {
        List<Conseiller> conseillers = conseillerDAO.getAll();

        Optional<Conseiller> conseiller = conseillers.stream()
                .filter(c -> id.equals(c.getId())).findFirst();

        return conseiller;
    }

    public List<Conseiller> getAll() throws Exception {
        return conseillerDAO.getAll();
    }

}

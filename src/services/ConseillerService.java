package services;

import DAO.ConseillerDAO;
import models.Conseiller;

import java.util.List;
import java.util.Optional;

public class ConseillerService {
    private ConseillerDAO conseillerDAO = new ConseillerDAO();

    public ConseillerService(ConseillerDAO conseillerDAO) {
        this.conseillerDAO = conseillerDAO;
    }

    public ConseillerService() {}

    public void addConseiller(Conseiller conseiller) throws Exception {
        conseillerDAO.addConseiller(conseiller);
    }

    public void deleteById(String id) throws Exception {
        conseillerDAO.deleteConseiller(id);
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

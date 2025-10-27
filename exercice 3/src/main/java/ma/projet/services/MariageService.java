package ma.projet.services;

import ma.projet.beans.Mariage;
import ma.projet.dao.GenericDao;

public class MariageService extends GenericDao<Mariage> {

    public MariageService() {
        super(Mariage.class);
    }
}


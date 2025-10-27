package ma.projet.services;

import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class HommeService extends GenericDao<Homme> {

    public HommeService() {
        super(Homme.class);
    }

    public List<Mariage> afficherEpousesEntreDates(Long hommeId, Date dateDebut, Date dateFin) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT m FROM Mariage m WHERE m.homme.id = :hommeId " +
                    "AND m.dateDebut >= :dateDebut AND m.dateDebut <= :dateFin";
            Query query = em.createQuery(jpql);
            query.setParameter("hommeId", hommeId);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> afficherMariagesAvecDetails(Long hommeId) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT m.dateDebut, m.dateFin, m.nombreEnfants, f.nom, f.prenom " +
                    "FROM Mariage m JOIN m.femme f " +
                    "WHERE m.homme.id = :hommeId " +
                    "ORDER BY m.dateDebut ASC";
            Query query = em.createQuery(jpql);
            query.setParameter("hommeId", hommeId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}


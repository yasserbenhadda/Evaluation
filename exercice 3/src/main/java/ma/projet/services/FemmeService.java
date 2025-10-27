package ma.projet.services;

import ma.projet.beans.Femme;
import ma.projet.dao.GenericDao;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class FemmeService extends GenericDao<Femme> {

    public FemmeService() {
        super(Femme.class);
    }

    public Long nombreEnfantsFemmeEntreDates(Long femmeId, Date dateDebut, Date dateFin) {
        EntityManager em = getEntityManager();
        try {
            String sql = "SELECT IFNULL(SUM(nbr_enfants), 0) FROM mariage WHERE femme_id = :femmeId AND date_debut >= :dateDebut AND date_debut <= :dateFin";
            Query query = em.createNativeQuery(sql);
            query.setParameter("femmeId", femmeId);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            Object result = query.getSingleResult();
            return ((Number) result).longValue();
        } finally {
            em.close();
        }
    }

    public List<Femme> femmesMarieesAuMoinsDeuxFois() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("femmesMarieesAuMoinsDeuxFois");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Long nombreHommesMariesAQuatreFemmesEntreDates(Date dateDebut, Date dateFin) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT DISTINCT h.id FROM Homme h " +
                    "WHERE (SELECT COUNT(m) FROM Mariage m WHERE m.homme.id = h.id " +
                    "AND m.dateDebut >= :dateDebut AND m.dateDebut <= :dateFin AND m.dateFin IS NULL) = 4";
            Query query = em.createQuery(jpql);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);

            return (long) query.getResultList().size();
        } finally {
            em.close();
        }
    }

    public Femme femmeLaPlusAgee() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT f FROM Femme f ORDER BY f.dateNaissance ASC";
            Query query = em.createQuery(jpql);
            query.setMaxResults(1);
            return (Femme) query.getSingleResult();
        } finally {
            em.close();
        }
    }
}


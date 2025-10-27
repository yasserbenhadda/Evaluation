package ma.projet.service;

import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class TacheService implements IDao<Tache> {
    private SessionFactory sessionFactory;

    public TacheService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean create(Tache o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.save(o);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Tache o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.update(o);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Tache o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.delete(o);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public Tache findById(int id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Tache.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Tache> findAll() {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("FROM Tache");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Tache> getTachesByPrice() {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.getNamedQuery("taches.byPrice");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Tache> getTachesBetweenDates(Date dateDebut, Date dateFin) {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery(
                    "SELECT t FROM Tache t WHERE " +
                            "t.dateDebutReelle BETWEEN :dateDebut AND :dateFin " +
                            "OR t.dateFinReelle BETWEEN :dateDebut AND :dateFin " +
                            "OR (t.dateDebutReelle <= :dateDebut AND t.dateFinReelle >= :dateFin)"
            );
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public void afficherTachesPrixSuperieur() {
        List<Tache> taches = getTachesByPrice();
        if (taches == null || taches.isEmpty()) {
            System.out.println("Aucune tâche avec un prix supérieur à 1000 DH.");
            return;
        }

        System.out.println("\nTâches dont le prix est supérieur à 1000 DH:");
        System.out.printf("%-4s %-20s %-10s %-20s%n", "ID", "Nom", "Prix", "Projet");
        System.out.println("--------------------------------------------------------");
        for (Tache tache : taches) {
            System.out.printf("%-4d %-20s %-10.2f %-20s%n",
                    tache.getId(),
                    tache.getNom(),
                    tache.getPrix(),
                    tache.getProjet().getNom()
            );
        }
    }

    public void afficherTachesEntreDates(Date dateDebut, Date dateFin) {
        List<Tache> taches = getTachesBetweenDates(dateDebut, dateFin);
        if (taches == null || taches.isEmpty()) {
            System.out.println("Aucune tâche réalisée entre ces dates.");
            return;
        }

        System.out.println("\nTâches réalisées entre " + dateDebut + " et " + dateFin + ":");
        System.out.printf("%-4s %-20s %-20s %-20s%n", "ID", "Nom", "Date Début Réelle", "Date Fin Réelle");
        System.out.println("--------------------------------------------------------");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        for (Tache tache : taches) {
            System.out.printf("%-4d %-20s %-20s %-20s%n",
                    tache.getId(),
                    tache.getNom(),
                    sdf.format(tache.getDateDebutReelle()),
                    sdf.format(tache.getDateFinReelle())
            );
        }
    }
}

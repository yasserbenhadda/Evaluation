package ma.projet.service;

import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.List;

public class ProjetService implements IDao<Projet> {
    private SessionFactory sessionFactory;

    public ProjetService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean create(Projet o) {
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
    public boolean update(Projet o) {
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
    public boolean delete(Projet o) {
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
    public Projet findById(int id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Projet.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Projet> findAll() {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("FROM Projet");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Tache> getTachesPlanifiees(int projetId) {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery(
                    "SELECT t FROM Tache t WHERE t.projet.id = :projetId " +
                            "AND t.dateDebutPlanifie IS NOT NULL AND t.dateFinPlanifie IS NOT NULL"
            );
            query.setParameter("projetId", projetId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public void afficherTachesRealisees(int projetId) {
        Session session = sessionFactory.openSession();
        try {
            Projet projet = session.get(Projet.class, projetId);
            if (projet == null) {
                System.out.println("Projet introuvable!");
                return;
            }

            Query query = session.createQuery(
                    "SELECT t FROM Tache t WHERE t.projet.id = :projetId " +
                            "AND t.dateDebutReelle IS NOT NULL AND t.dateFinReelle IS NOT NULL " +
                            "ORDER BY t.dateDebutReelle"
            );
            query.setParameter("projetId", projetId);
            List<Tache> taches = query.getResultList();

            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
            System.out.println("\nProjet : " + projet.getId() +
                    "      Nom : " + projet.getNom() +
                    "     Date début : " + sdf.format(projet.getDateDebut()));
            System.out.println("Liste des tâches:");
            System.out.printf("%-4s %-15s %-20s %-20s%n", "Num", "Nom", "Date Début Réelle", "Date Fin Réelle");
            System.out.println("--------------------------------------------------------");

            SimpleDateFormat sdfShort = new SimpleDateFormat("dd/MM/yyyy");
            for (Tache tache : taches) {
                System.out.printf("%-4d %-15s %-20s %-20s%n",
                        tache.getId(),
                        tache.getNom(),
                        sdfShort.format(tache.getDateDebutReelle()),
                        sdfShort.format(tache.getDateFinReelle())
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

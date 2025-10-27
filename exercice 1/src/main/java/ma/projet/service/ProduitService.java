package ma.projet.service;

import ma.projet.classes.Categorie;
import ma.projet.classes.Produit;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProduitService implements IDao<Produit> {

    @Override
    public boolean create(Produit o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Produit o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Produit o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public Produit findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(Produit.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Produit> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("FROM Produit", Produit.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    // Affichage de la liste des produits par catégorie
    public List<Produit> getProduitsByCategorie(Categorie categorie) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "FROM Produit p WHERE p.categorie = :categorie";
            return session.createQuery(hql, Produit.class)
                    .setParameter("categorie", categorie)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    // Affichage des produits commandés entre deux dates
    public List<Produit> getProduitsCommandeBetweenDates(Date dateDebut, Date dateFin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT DISTINCT p FROM Produit p " +
                    "JOIN p.categorie c " +
                    "WHERE EXISTS (" +
                    "SELECT lc FROM LigneCommande lc " +
                    "JOIN lc.commande cmd " +
                    "WHERE lc.produit = p " +
                    "AND cmd.dateCommande BETWEEN :dateDebut AND :dateFin" +
                    ")";
            return session.createQuery(hql, Produit.class)
                    .setParameter("dateDebut", dateDebut)
                    .setParameter("dateFin", dateFin)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    // Affichage des produits commandés dans une commande donnée
    public void afficherProduitsParCommande(int commandeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT c.id, c.dateCommande, p.reference, p.prix, lc.quantite " +
                    "FROM Commande c " +
                    "JOIN c.lignesCommande lc " +
                    "JOIN lc.produit p " +
                    "WHERE c.id = :commandeId";

            List<Object[]> results = session.createQuery(hql, Object[].class)
                    .setParameter("commandeId", commandeId)
                    .getResultList();

            if (!results.isEmpty()) {
                Object[] firstRow = results.get(0);
                Integer id = (Integer) firstRow[0];
                Date date = (Date) firstRow[1];

                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", java.util.Locale.FRENCH);
                System.out.println("\nCommande : " + id + "     Date : " + sdf.format(date));
                System.out.println("Liste des produits :");
                System.out.println("Référence   Prix    Quantité");

                for (Object[] row : results) {
                    String ref = (String) row[2];
                    Double prix = (Double) row[3];
                    Integer qte = (Integer) row[4];
                    System.out.printf("%-10s %-7s %d%n", ref, prix + " DH", qte);
                }
            } else {
                System.out.println("Aucune commande trouvée avec l'ID : " + commandeId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Affichage des produits dont le prix > 100 DH (requête nommée)
    public List<Produit> getProduitsPrixSuperieur(double prix) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createNamedQuery("Produit.findByPrix", Produit.class)
                    .setParameter("prix", prix)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}

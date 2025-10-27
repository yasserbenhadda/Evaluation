package ma.projet.service;

import ma.projet.classes.EmployeTache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeTacheService implements IDao<EmployeTache> {
    private SessionFactory sessionFactory;

    public EmployeTacheService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean create(EmployeTache o) {
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
    public boolean update(EmployeTache o) {
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
    public boolean delete(EmployeTache o) {
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
    public EmployeTache findById(int id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(EmployeTache.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<EmployeTache> findAll() {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("FROM EmployeTache");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}

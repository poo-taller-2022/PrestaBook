package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Lector;
import ar.edu.uner.prestabook.persistence.ILectorDAO;

public class LectorDAO implements ILectorDAO {

    /**
     * Singleton instance of the class
     */
    private static final LectorDAO instance = new LectorDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private LectorDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static LectorDAO getInstance() {
        return instance;
    }

    @Override
    public List<Lector> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Lector", Lector.class).list();

    }

    @Override
    public Lector findById(Object dni) {
        return HibernateConnection.getCurrentSession().get(Lector.class, (String) dni);

    }

    @Override
    public Integer insert(Lector lector) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Integer id = (Integer) HibernateConnection.getCurrentSession().save(lector);
        tx.commit();
        return id;
    }

    @Override
    public Integer update(Lector lector) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(lector);
        tx.commit();
        return 0;
    }

    @Override
    public Integer delete(Lector lector) {
        return 0;
    }

}

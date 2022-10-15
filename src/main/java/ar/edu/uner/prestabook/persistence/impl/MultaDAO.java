package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Multa;
import ar.edu.uner.prestabook.persistence.IMultaDAO;

public class MultaDAO implements IMultaDAO {

    /**
     * Singleton instance of the class
     */
    private static final MultaDAO instance = new MultaDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private MultaDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static MultaDAO getInstance() {
        return instance;
    }

    @Override
    public List<Multa> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Multa", Multa.class).list();
    }

    @Override
    public Multa findById(Object id) {
        return HibernateConnection.getCurrentSession().get(Multa.class, (Long) id);

    }

    @Override
    public Integer insert(Multa multa) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Integer id = (Integer) HibernateConnection.getCurrentSession().save(multa);
        tx.commit();
        return id;
    }

    @Override
    public Integer update(Multa multa) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(multa);
        tx.commit();
        return 0;
    }

    @Override
    public Integer delete(Multa t) {
        return null;
    }

}
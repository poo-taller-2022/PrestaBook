package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Formato;
import ar.edu.uner.prestabook.persistence.IFormatoDAO;

public class FormatoDAO implements IFormatoDAO {

    /**
     * Singleton instance of the class
     */
    private static final FormatoDAO instance = new FormatoDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private FormatoDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static FormatoDAO getInstance() {
        return instance;
    }

    @Override
    public List<Formato> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Formato", Formato.class).list();

    }

    @Override
    public Formato findById(Object id) {
        return HibernateConnection.getCurrentSession().get(Formato.class, (Integer) id);

    }

    @Override
    public Formato insert(Formato formato) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Integer id = (Integer) HibernateConnection.getCurrentSession().save(formato);
        tx.commit();
        formato.setId(id);
        return formato;
    }

    @Override
    public Formato update(Formato formato) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(formato);
        tx.commit();
        return formato;
    }

    @Override
    public Integer delete(Formato formato) {
        return 0;
    }

}

package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Edicion;
import ar.edu.uner.prestabook.persistence.IEdicionDAO;

public class EdicionDAO implements IEdicionDAO {

    /**
     * Singleton instance of the class
     */
    private static final EdicionDAO instance = new EdicionDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private EdicionDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static EdicionDAO getInstance() {
        return instance;
    }

    @Override
    public List<Edicion> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Edicion", Edicion.class).list();
    }

    @Override
    public Edicion findById(Object id) {
        return HibernateConnection.getCurrentSession().get(Edicion.class, (Integer) id);

    }

    @Override
    public Integer insert(Edicion edicion) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Integer id = (Integer) HibernateConnection.getCurrentSession().save(edicion);
        tx.commit();
        return id;
    }

    @Override
    public Integer update(Edicion edicion) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(edicion);
        tx.commit();
        return 0;
    }

    @Override
    public Integer delete(Edicion edicion) {
        return 0;
    }

}

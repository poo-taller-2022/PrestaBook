package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Coleccion;
import ar.edu.uner.prestabook.persistence.IColeccionDAO;

public class ColeccionDAO implements IColeccionDAO {

    /**
     * Singleton instance of the class
     */
    private static final ColeccionDAO instance = new ColeccionDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private ColeccionDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static ColeccionDAO getInstance() {
        return instance;
    }

    @Override
    public List<Coleccion> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Coleccion", Coleccion.class).list();

    }

    @Override
    public Coleccion findById(Object id) {
        return HibernateConnection.getCurrentSession().get(Coleccion.class, (Long) id);
    }

    @Override
    public Integer insert(Coleccion coleccion) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long id = (Long) HibernateConnection.getCurrentSession().save(coleccion);
        tx.commit();
        return id.intValue();
    }

    @Override
    public Integer update(Coleccion coleccion) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(coleccion);
        tx.commit();
        return 0;
    }

    @Override
    public Integer delete(Coleccion t) {
        return null;
    }

}

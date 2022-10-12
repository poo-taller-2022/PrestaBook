package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.AreaTematica;
import ar.edu.uner.prestabook.persistence.IAreaTematicaDAO;

public class AreaTematicaDAO implements IAreaTematicaDAO {

    /**
     * Singleton instance of the class
     */
    private static final AreaTematicaDAO instance = new AreaTematicaDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private AreaTematicaDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static AreaTematicaDAO getInstance() {
        return instance;
    }

    @Override
    public List<AreaTematica> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from AreaTematica", AreaTematica.class).list();
    }

    @Override
    public AreaTematica findById(Object id) {
        return HibernateConnection.getCurrentSession().get(AreaTematica.class, (Integer) id);
    }

    @Override
    public Integer insert(AreaTematica areaTematica) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Integer id = (Integer) HibernateConnection.getCurrentSession().save(areaTematica);
        tx.commit();
        return id;
    }

    @Override
    public Integer update(AreaTematica areaTematica) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(areaTematica);
        tx.commit();
        return 0;
    }

    @Override
    public Integer delete(AreaTematica areaTematica) {
        return 0;
    }

}

package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.persistence.IEjemplarDAO;

public class EjemplarDAO implements IEjemplarDAO {

    /**
     * Singleton instance of the class
     */
    private static final EjemplarDAO instance = new EjemplarDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private EjemplarDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static EjemplarDAO getInstance() {
        return instance;
    }

    @Override
    public List<Ejemplar> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Ejemplar", Ejemplar.class).list();
    }

    @Override
    public Ejemplar findById(Object isbn) {
        return HibernateConnection.getCurrentSession().get(Ejemplar.class, (String) isbn);
    }

    @Override
    public Integer insert(Ejemplar ejemplar) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long id = (Long) HibernateConnection.getCurrentSession().save(ejemplar);
        tx.commit();
        return id.intValue();
    }

    @Override
    public Integer update(Ejemplar ejemplar) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(ejemplar);
        tx.commit();
        return 0;
    }

    @Override
    public Integer delete(Ejemplar t) {
        return null;
    }

    @Override
    public List<Ejemplar> findAllByObraIsbn(String isbn) {
        String hql = String.format("from Ejemplar e where e.isbn = %s", isbn);
        return HibernateConnection.getCurrentSession().createQuery(hql, Ejemplar.class).list();

    }

}

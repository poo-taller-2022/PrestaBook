package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import javax.persistence.Query;

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
    public Lector insert(Lector lector) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long documento = (Long) HibernateConnection.getCurrentSession().save(lector);
        tx.commit();
        lector.setDocumento(documento);
        return lector;
    }


    @Override
    public Lector update(Lector lector) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(lector);
        tx.commit();
        return lector;
    }


    @Override
    public Long countFinesById(Long documento) {
        Query query = HibernateConnection.getCurrentSession().createQuery(
                String.format("select count(*) from Multa m where m.lector.documento = '%d'", documento));
        return (Long) query.getSingleResult();
    }

}

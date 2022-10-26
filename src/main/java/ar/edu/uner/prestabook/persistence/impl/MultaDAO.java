package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Multa;
import ar.edu.uner.prestabook.persistence.IMultaDAO;

/**
 * Data access object to fine class,
 * interacts with table Multas in database
 */
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
     * Creates an instance of the class  
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
    public Multa insert(Multa multa) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long id = (Long) HibernateConnection.getCurrentSession().save(multa);
        tx.commit();
        multa.setId(id);
        return multa;
    }

    
    @Override
    public Multa update(Multa multa) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(multa);
        tx.commit();
        return multa;
    }
    
    @Override
    public List<Multa> findByAllDocumentoLector(Long documentoLector) {
        String hql = String.format("from Multa p where p.lector.documento = '%s'", documentoLector);
        return HibernateConnection.getCurrentSession().createQuery(hql, Multa.class).list();
    }

}

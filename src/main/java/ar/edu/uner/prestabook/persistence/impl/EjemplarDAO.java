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

    
    /**
     * 
     * get all entities in table ejemplares from database
     * @return list of objects Ejemplar
     */
    @Override
    public List<Ejemplar> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Ejemplar", Ejemplar.class).list();
    }

    
    /**
     * get an entity from table ejemplares where id match with param 
     * @return Ejemplar object
     * @param id of Ejemplar
     */
    @Override
    public Ejemplar findById(Object id) {
        return HibernateConnection.getCurrentSession().get(Ejemplar.class, (Long) id);
    }

    
    /**
     * inserts an entity Ejemplar in database
     * @return Ejemplar The most recently object inserted
     * @param Ejemplar object
     */
    @Override
    public Ejemplar insert(Ejemplar ejemplar) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long id = (Long) HibernateConnection.getCurrentSession().save(ejemplar);
        tx.commit();
        ejemplar.setId(id);
        return ejemplar;
    }

    
    /**
     * update table ejemplares in database with new object Ejemplar
     * @return Ejemplar The object updated
     * @param Ejemplar object to update
     */
    @Override
    public Ejemplar update(Ejemplar ejemplar) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(ejemplar);
        tx.commit();
        return ejemplar;
    }

    /**
     * get all entities from table ejemplares where isbn match with param 
     * @return list of objects Ejemplar
     * @param isbn of Ejemplar
     */
    @Override
    public List<Ejemplar> findAllByObraIsbn(String isbn) {
        String hql = String.format("from Ejemplar e where e.isbnObra = '%s'", isbn);
        return HibernateConnection.getCurrentSession().createQuery(hql, Ejemplar.class).list();

    }

}

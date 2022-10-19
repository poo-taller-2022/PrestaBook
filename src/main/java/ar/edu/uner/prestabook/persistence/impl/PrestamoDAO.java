package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Prestamo;
import ar.edu.uner.prestabook.persistence.IPrestamoDAO;

public class PrestamoDAO implements IPrestamoDAO {

    /**
     * Singleton instance of the class
     */
    private static final PrestamoDAO instance = new PrestamoDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private PrestamoDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static PrestamoDAO getInstance() {
        return instance;
    }

    
    /**
     * 
     * get all entities in table prestamos from database
     * @return list of objects Prestamo
     */
    @Override
    public List<Prestamo> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Prestamo", Prestamo.class).list();
    }

    
    /**
     * get an entity from table prestamos where id match with param 
     * @return Prestamo object
     * @param id of Prestamo
     */
    @Override
    public Prestamo findById(Object id) {
        return HibernateConnection.getCurrentSession().get(Prestamo.class, (Long) id);

    }

    
    /**
     * inserts an entity Prestamo in database
     * @return Prestamo The most recently object inserted
     * @param Prestamo object
     */
    @Override
    public Prestamo insert(Prestamo prestamo) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long id = (Long) HibernateConnection.getCurrentSession().save(prestamo);
        tx.commit();
        prestamo.setId(id);
        return prestamo;
    }

    
    /**
     * update table prestamos in database with new object Prestamo
     * @return Prestamo The object updated
     * @param Prestamo object to update
     */
    @Override
    public Prestamo update(Prestamo prestamo) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(prestamo);
        tx.commit();
        return prestamo;
    }
    
    
    /**
     * get all entities from table prestamos where lector_documento match with param 
     * @return list of objects Prestamo
     * @param documentoLector of lector in Prestamo
     */
    @Override
    public List<Prestamo> findAllByLectorId(Long documentoLector) {
        String hql = String.format("from Prestamo p where p.lector = '%s'", documentoLector);
        return HibernateConnection.getCurrentSession().createQuery(hql, Prestamo.class).list();

    }

}

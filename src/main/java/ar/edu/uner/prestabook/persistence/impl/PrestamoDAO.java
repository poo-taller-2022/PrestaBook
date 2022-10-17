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

    @Override
    public List<Prestamo> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Prestamo", Prestamo.class).list();
    }

    @Override
    public Prestamo findById(Object id) {
        return HibernateConnection.getCurrentSession().get(Prestamo.class, (Long) id);

    }

    @Override
    public Prestamo insert(Prestamo prestamo) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long id = (Long) HibernateConnection.getCurrentSession().save(prestamo);
        tx.commit();
        prestamo.setId(id);
        return prestamo;
    }

    @Override
    public Prestamo update(Prestamo prestamo) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(prestamo);
        tx.commit();
        return prestamo;
    }

    @Override
    public Integer delete(Prestamo t) {
        return null;
    }

    @Override
    public List<Prestamo> findAllByLectorId(Long documentoLector) {
        String hql = String.format("from Prestamo p where p.lector = '%s'", documentoLector);
        return HibernateConnection.getCurrentSession().createQuery(hql, Prestamo.class).list();

    }

}

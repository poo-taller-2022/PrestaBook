package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import javax.persistence.Query;

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
        HibernateConnection.getCurrentSession().merge(prestamo);
        tx.commit();
        return prestamo;
    }

    @Override
    public List<Prestamo> findAllByLectorId(Long documentoLector) {
        String hql = String.format("from Prestamo p where p.lector = '%s'", documentoLector);
        return HibernateConnection.getCurrentSession().createQuery(hql, Prestamo.class).list();
    }
    
    @Override
    public Prestamo findByIdEjemplar(Long idEjemplar) {
        String hql = String.format("from Prestamo p where p.ejemplar.id = '%s'", idEjemplar);
        return HibernateConnection.getCurrentSession().createQuery(hql, Prestamo.class).uniqueResult(); 
    }

    @Override
    public Long countByObraIsbn(String isbn) {
        Query query = HibernateConnection.getCurrentSession().createQuery(
                String.format("select count(*) from Prestamo p where p.ejemplar.isbnObra = '%s'", isbn));
        return (Long) query.getSingleResult();
    }
    
    @Override
    public void delete(Long idPrestamo) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Query query = HibernateConnection.getCurrentSession().createQuery(
                String.format("delete Prestamo p where p.id = '%s'", idPrestamo));
        query.executeUpdate();
        tx.commit();
    }

}

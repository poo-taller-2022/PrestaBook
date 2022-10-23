package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Reserva;
import ar.edu.uner.prestabook.persistence.IReservaDAO;

public class ReservaDAO implements IReservaDAO {
    /**
     * Singleton instance of the class
     */
    private static final ReservaDAO instance = new ReservaDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private ReservaDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static ReservaDAO getInstance() {
        return instance;
    }


    @Override
    public List<Reserva> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Reserva", Reserva.class).list();
    }


    @Override
    public Reserva findById(Object id) {
        return HibernateConnection.getCurrentSession().get(Reserva.class, (Long) id);

    }


    @Override
    public Reserva insert(Reserva reserva) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long id = (Long) HibernateConnection.getCurrentSession().save(reserva);
        tx.commit();
        reserva.setId(id);
        return reserva;
    }


    @Override
    public Reserva update(Reserva reserva) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().merge(reserva);
        tx.commit();
        return reserva;
    }

    @Override
    public Long countByObraIsbn(String isbn) {
        Query query = HibernateConnection.getCurrentSession().createQuery(
                String.format("select count(*) from Reserva r where r.ejemplar.isbnObra = '%s' and r.isActive = true", isbn));
        return (Long) query.getSingleResult();
    }
    
    @Override
    public Reserva findByIdEjemplar(Long idEjemplar) {
        String hql = String.format("from Reserva p where p.ejemplar.id = '%s'", idEjemplar);
        return HibernateConnection.getCurrentSession().createQuery(hql, Reserva.class).uniqueResult();
    }

}
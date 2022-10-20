package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

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
        HibernateConnection.getCurrentSession().update(reserva);
        tx.commit();
        return reserva;
    }

}
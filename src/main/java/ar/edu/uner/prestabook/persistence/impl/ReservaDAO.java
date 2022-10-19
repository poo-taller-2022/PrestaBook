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

    
    /**
     * 
     * get all entities in table reservas from database
     * @return list of objects Reserva
     */
    @Override
    public List<Reserva> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Reserva", Reserva.class).list();
    }

    
    /**
     * get an entity from table reservas where id match with param 
     * @return Reserva object
     * @param id of Reserva
     */
    @Override
    public Reserva findById(Object id) {
        return HibernateConnection.getCurrentSession().get(Reserva.class, (Long) id);

    }

    
    /**
     * inserts an entity Reserva in database
     * @return Reserva The most recently object inserted
     * @param Reserva object
     */
    @Override
    public Reserva insert(Reserva reserva) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long id = (Long) HibernateConnection.getCurrentSession().save(reserva);
        tx.commit();
        reserva.setId(id);
        return reserva;
    }

    
    /**
     * update table reservas in database with new object Reserva
     * @return Reserva The object updated
     * @param Reserva object to update
     */
    @Override
    public Reserva update(Reserva reserva) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(reserva);
        tx.commit();
        return reserva;
    }

}
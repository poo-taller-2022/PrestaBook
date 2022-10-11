package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Docente;
import ar.edu.uner.prestabook.persistence.IDocenteDAO;

public class DocenteDAO implements IDocenteDAO {

    /**
     * Singleton instance of the class
     */
    private static final DocenteDAO instance = new DocenteDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private DocenteDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static DocenteDAO getInstance() {
        return instance;
    }

    @Override
    public List<Docente> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Docente", Docente.class).list();

    }

    @Override
    public Docente findById(Object dni) {
        return HibernateConnection.getCurrentSession().get(Docente.class, (String) dni);

    }

    @Override
    public Integer insert(Docente docente) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Integer id = (Integer) HibernateConnection.getCurrentSession().save(docente);
        tx.commit();
        return id;
    }

    @Override
    public Integer update(Docente docente) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(docente);
        tx.commit();
        return 0;
    }

    @Override
    public Integer delete(Docente docente) {
        return 0;
    }    

}

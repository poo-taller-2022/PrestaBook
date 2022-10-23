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
    public Docente insert(Docente docente) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long documento = (Long) HibernateConnection.getCurrentSession().save(docente);
        tx.commit();
        docente.setDocumento(documento);
        return docente;
    }

    @Override
    public Docente update(Docente docente) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(docente);
        tx.commit();
        return docente;
    }

    @Override
    public Docente findByEmail(String email) {
        String hql = String.format("from Docente d where d.email = '%s'", email);
        return HibernateConnection.getCurrentSession().createQuery(hql, Docente.class).uniqueResult();

    }

}

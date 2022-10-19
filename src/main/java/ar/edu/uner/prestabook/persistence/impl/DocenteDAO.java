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

    
    /**
     * 
     * get all entities in table docentes from database
     * @return list of objects Docente
     */
    @Override
    public List<Docente> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Docente", Docente.class).list();

    }
    
    
    /**
     * get an entity from table docentes where dni match with param 
     * @return Docente object
     * @param dni of Docente
     */
    @Override
    public Docente findById(Object dni) {
        return HibernateConnection.getCurrentSession().get(Docente.class, (String) dni);

    }

    
    /**
     * inserts an entity Docente in database
     * @return Docente The most recently object inserted
     * @param Docente object
     */
    @Override
    public Docente insert(Docente docente) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long documento = (Long) HibernateConnection.getCurrentSession().save(docente);
        tx.commit();
        docente.setDocumento(documento);
        return docente;
    }
    
    
    /**
     * update table docentes in database with new object Docente
     * @return Docente The object updated
     * @param Docente object to update
     */
    @Override
    public Docente update(Docente docente) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(docente);
        tx.commit();
        return docente;
    }


}

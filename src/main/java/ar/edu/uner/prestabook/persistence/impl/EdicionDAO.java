package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Edicion;
import ar.edu.uner.prestabook.persistence.IEdicionDAO;

public class EdicionDAO implements IEdicionDAO {

    /**
     * Singleton instance of the class
     */
    private static final EdicionDAO instance = new EdicionDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private EdicionDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static EdicionDAO getInstance() {
        return instance;
    }

    
    /**
     * 
     * get all entities in table ediciones from database
     * @return list of objects Edicion
     */
    @Override
    public List<Edicion> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Edicion", Edicion.class).list();
    }

    
    /**
     * get an entity from table ediciones where id match with param 
     * @return Edicion object
     * @param id of Edicion
     */
    @Override
    public Edicion findById(Object id) {
        return HibernateConnection.getCurrentSession().get(Edicion.class, (Integer) id);

    }

    
    /**
     * inserts an entity Edicion in database
     * @return Edicion The most recently object inserted
     * @param Edicion object
     */
    @Override
    public Edicion insert(Edicion edicion) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long id = (Long) HibernateConnection.getCurrentSession().save(edicion);
        tx.commit();
        edicion.setId(id);
        return edicion;
    }

    
    /**
     * update table ediciones in database with new object Edicion
     * @return Edicion The object updated
     * @param Edicion object to update
     */
    @Override
    public Edicion update(Edicion edicion) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(edicion);
        tx.commit();
        return edicion;
    }

}

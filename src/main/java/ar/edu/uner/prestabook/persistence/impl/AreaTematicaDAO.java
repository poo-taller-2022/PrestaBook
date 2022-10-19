package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.AreaTematica;
import ar.edu.uner.prestabook.persistence.IAreaTematicaDAO;

public class AreaTematicaDAO implements IAreaTematicaDAO {

    /**
     * Singleton instance of the class
     */
    private static final AreaTematicaDAO instance = new AreaTematicaDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private AreaTematicaDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static AreaTematicaDAO getInstance() {
        return instance;
    }


    /**
     * 
     * get all entities in table areas_tematicas from database
     * @return list of objects AreaTematica
     */
    @Override
    public List<AreaTematica> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from AreaTematica", AreaTematica.class).list();
    }

    
    /**
     * get an entity from table areas_tematicas where id match with param 
     * @return areaTematica object
     * @param id of areaTematica
     */
    @Override
    public AreaTematica findById(Object id) {
        return HibernateConnection.getCurrentSession().get(AreaTematica.class, (Integer) id);
    }

    
    /**
     * inserts an entity areaTematica in database
     * @return areaTematica the most recently object inserted
     * @param areaTematica object
     */
    @Override
    public AreaTematica insert(AreaTematica areaTematica) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Integer id = (Integer) HibernateConnection.getCurrentSession().save(areaTematica);
        tx.commit();
        areaTematica.setId(id);
        return areaTematica;
    }

    
    /**
     * update table areas_tematicas in database with new object areaTematica
     * @return areaTematica the object updated
     * @param areaTematica object to update
     */
    @Override
    public AreaTematica update(AreaTematica areaTematica) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(areaTematica);
        tx.commit();
        return areaTematica;
    }

}

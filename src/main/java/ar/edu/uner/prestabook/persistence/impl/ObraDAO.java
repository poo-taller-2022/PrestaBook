package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.persistence.IObraDAO;

public class ObraDAO implements IObraDAO {

    /**
     * Singleton instance of the class
     */
    private static final ObraDAO instance = new ObraDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private ObraDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static ObraDAO getInstance() {
        return instance;
    }

    
    /**
     * 
     * get all entities in table obras from database
     * @return list of objects Obra
     */
    @Override
    public List<Obra> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Obra", Obra.class).list();
    }

    
    /**
     * get an entity from table Obras where id match with param 
     * @return Obra object
     * @param id of Obra
     */
    @Override
    public Obra findById(Object id) {
        return HibernateConnection.getCurrentSession().get(Obra.class, (String) id);

    }

    
    /**
     * inserts an entity Obra in database
     * @return Obra The most recently object inserted
     * @param Obra object
     */
    @Override
    public Obra insert(Obra obra) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        String isbn = (String) HibernateConnection.getCurrentSession().save(obra);
        tx.commit();
        obra.setIsbn(isbn);
        return obra;
    }

    
    /**
     * update table obras in database with new object Obra
     * @return Obra The object updated
     * @param Obra object to update
     */
    @Override
    public Obra update(Obra obra) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(obra);
        tx.commit();
        return obra;
    }

    
    /**
     * get all entities from table obras where id match with param 
     * @return list of objects Obra
     * @param id of Obra
     */
    public List<Obra> filtrarPorTema(Integer id) {
        return HibernateConnection.getCurrentSession()
                .createQuery(String.format("FROM Obra obra WHERE obra.area = %s", id), Obra.class).list();
    }

}
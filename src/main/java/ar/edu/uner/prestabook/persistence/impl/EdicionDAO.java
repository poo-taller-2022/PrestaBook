package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Edicion;
import ar.edu.uner.prestabook.persistence.IEdicionDAO;

/**
 * Data access object to edition class,
 * interacts with table Ediciones in database
 */
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
     * Creates an instance of the class
     * @return the singleton instance of the class
     */
    public static EdicionDAO getInstance() {
        return instance;
    }


    @Override
    public List<Edicion> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Edicion", Edicion.class).list();
    }


    @Override
    public Edicion findById(Object id) {
        return HibernateConnection.getCurrentSession().get(Edicion.class, (Long) id);

    }


    @Override
    public Edicion insert(Edicion edicion) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long id = (Long) HibernateConnection.getCurrentSession().save(edicion);
        tx.commit();
        edicion.setId(id);
        return edicion;
    }


    @Override
    public Edicion update(Edicion edicion) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(edicion);
        tx.commit();
        return edicion;
    }
	
	@Override
    public List<Edicion> findByAllObraIsbn(String isbn) {
        String hql = String.format("from Edicion e where e.isbnObra = '%s'", isbn);
        return HibernateConnection.getCurrentSession().createQuery(hql, Edicion.class).list();

    }

}

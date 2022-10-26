package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.persistence.IObraDAO;

/**
 * Data access object to book class,
 * interacts with table Obra in database
 *
 */
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
     * Creates an instance of the class 
     * @return the singleton instance of the class
     */
    public static ObraDAO getInstance() {
        return instance;
    }


    @Override
    public List<Obra> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Obra", Obra.class).list();
    }


    @Override
    public Obra findById(Object id) {
        return HibernateConnection.getCurrentSession().get(Obra.class, (String) id);

    }


    @Override
    public Obra insert(Obra obra) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        String isbn = (String) HibernateConnection.getCurrentSession().save(obra);
        tx.commit();
        obra.setIsbn(isbn);
        return obra;
    }


    @Override
    public Obra update(Obra obra) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(obra);
        tx.commit();
        return obra;
    }


    public List<Obra> filtrarPorTema(Integer id) {
        return HibernateConnection.getCurrentSession()
                .createQuery(String.format("FROM Obra obra WHERE obra.area = %s", id), Obra.class).list();
    }

}
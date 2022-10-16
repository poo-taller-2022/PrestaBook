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

    @Override
    public List<Obra> findAll() {
        String hql = String.format("from Obra o where type(o) = %s", Obra.class.getName());
        return HibernateConnection.getCurrentSession().createQuery(hql, Obra.class).list();
    }

    @Override
    public Obra findById(Object isbn) {
        return HibernateConnection.getCurrentSession().get(Obra.class, (String) isbn);

    }

    @Override
    public Integer insert(Obra obra) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long id = (Long) HibernateConnection.getCurrentSession().save(obra);
        tx.commit();
        return id.intValue();
    }

    @Override
    public Integer update(Obra obra) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(obra);
        tx.commit();
        return 0;
    }

    @Override
    public Integer delete(Obra t) {
        return null;
    }

    public List<Obra> filtrarPorTema(Integer id) {
        return HibernateConnection.getCurrentSession()
                .createQuery(String.format("FROM Obra obra WHERE obra.area = %s", id), Obra.class).list();
    }

}
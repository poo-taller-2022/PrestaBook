package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.TipoObra;
import ar.edu.uner.prestabook.persistence.ITipoObraDAO;

public class TipoObraDAO implements ITipoObraDAO {

    /**
     * Singleton instance of the class
     */
    private static final TipoObraDAO instance = new TipoObraDAO();
    private final Session session = HibernateConnection.getCurrentSession();

    /**
     * Private constructor to avoid instantiation
     */
    private TipoObraDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static TipoObraDAO getInstance() {
        return instance;
    }


    @Override
    public List<TipoObra> findAll() {
        String hql = "from TipoObra";
        return session.createQuery(hql, TipoObra.class).list();
    }


    @Override
    public TipoObra findById(Object id) {
        return HibernateConnection.getCurrentSession().get(TipoObra.class, (Integer) id);
    }


    @Override
    public TipoObra insert(TipoObra tipoObra) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Integer id = (Integer) HibernateConnection.getCurrentSession().save(tipoObra);
        tx.commit();
        tipoObra.setId(id);
        return tipoObra;
    }


    @Override
    public TipoObra update(TipoObra tipoObra) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(tipoObra);
        tx.commit();
        return tipoObra;
    }

}

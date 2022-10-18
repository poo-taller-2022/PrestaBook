package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.CodigoIdentificatorio;
import ar.edu.uner.prestabook.persistence.ICodigoIdentificatorioDAO;

public class CodigoIdentificatorioDAO implements ICodigoIdentificatorioDAO {

    /**
     * Singleton instance of the class
     */
    private static final CodigoIdentificatorioDAO instance = new CodigoIdentificatorioDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private CodigoIdentificatorioDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static CodigoIdentificatorioDAO getInstance() {
        return instance;
    }

    @Override
    public List<CodigoIdentificatorio> findAll() {
        return HibernateConnection.getCurrentSession()
                .createQuery("from CodigoIdentificatorio", CodigoIdentificatorio.class).list();

    }

    @Override
    public CodigoIdentificatorio findById(Object id) {
        return HibernateConnection.getCurrentSession().get(CodigoIdentificatorio.class, (Long) id);
    }

    @Override
    public CodigoIdentificatorio insert(CodigoIdentificatorio codigoIdentificatorio) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long codigo = (Long) HibernateConnection.getCurrentSession().save(codigoIdentificatorio);
        tx.commit();
        codigoIdentificatorio.setCodigo(codigo);
        return codigoIdentificatorio;
    }

    @Override
    public CodigoIdentificatorio update(CodigoIdentificatorio codigoIdentificatorio) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().merge(codigoIdentificatorio);
        tx.commit();
        return codigoIdentificatorio;
    }

    @Override
    public Integer delete(CodigoIdentificatorio cod) {
        return 0;
    }

}

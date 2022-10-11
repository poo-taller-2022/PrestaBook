package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Funcionario;
import ar.edu.uner.prestabook.persistence.IFuncionarioDAO;

public class FuncionarioDAO implements IFuncionarioDAO {

    /**
     * Singleton instance of the class
     */
    private static final FuncionarioDAO instance = new FuncionarioDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private FuncionarioDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static FuncionarioDAO getInstance() {
        return instance;
    }

    @Override
    public List<Funcionario> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Funcionario", Funcionario.class).list();

    }

    @Override
    public Funcionario findById(Object dni) {
        return HibernateConnection.getCurrentSession().get(Funcionario.class, (String) dni);

    }

    @Override
    public Integer insert(Funcionario funcionario) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Integer id = (Integer) HibernateConnection.getCurrentSession().save(funcionario);
        tx.commit();
        return id;
    }

    @Override
    public Integer update(Funcionario funcionario) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(funcionario);
        tx.commit();
        return 0;
    }

    @Override
    public Integer delete(Funcionario funcionario) {
        return 0;
    }
}

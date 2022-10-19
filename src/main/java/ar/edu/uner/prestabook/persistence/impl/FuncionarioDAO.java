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

    
    /**
     * 
     * get all entities in table funcionarios from database
     * @return list of objects Funcionario
     */
    @Override
    public List<Funcionario> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Funcionario", Funcionario.class).list();

    }

    
    /**
     * get an entity from table funcionarios where dni match with param 
     * @return Funcionario object
     * @param dni of Funcionario
     */
    @Override
    public Funcionario findById(Object dni) {
        return HibernateConnection.getCurrentSession().get(Funcionario.class, (String) dni);

    }

    
    /**
     * inserts an entity Funcionario in database
     * @return Funcionario The most recently object inserted
     * @param Funcionario object
     */
    @Override
    public Funcionario insert(Funcionario funcionario) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long documento = (Long) HibernateConnection.getCurrentSession().save(funcionario);
        tx.commit();
        funcionario.setDocumento(documento);
        return funcionario;
    }

    
    /**
     * update table funcionarios in database with new object Funcionario
     * @return Funcionario The object updated
     * @param Funcionario object to update
     */
    @Override
    public Funcionario update(Funcionario funcionario) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(funcionario);
        tx.commit();
        return funcionario;
    }

}

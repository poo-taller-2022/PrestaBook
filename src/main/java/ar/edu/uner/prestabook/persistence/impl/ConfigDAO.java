package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Config;
import ar.edu.uner.prestabook.persistence.IConfigDAO;

/**
 * Data access object to config class,
 * 
 */
public class ConfigDAO implements IConfigDAO {

    /**
     * Singleton instance of the class
     */
    private static final ConfigDAO instance = new ConfigDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private ConfigDAO() {
    }

    /**
     * Creates an instance of the class
     * @return the singleton instance of the class
     */
    public static ConfigDAO getInstance() {
        return instance;
    }

    @Override
    public List<Config> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Config", Config.class).list();
    }

    @Override
    public Config findById(Object id) {
        return HibernateConnection.getCurrentSession().get(Config.class, (String) id);

    }

    @Override
    public Config insert(Config config) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().save(config);
        tx.commit();
        return config;
    }

    @Override
    public Config update(Config config) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().merge(config);
        tx.commit();
        return config;
    }

}

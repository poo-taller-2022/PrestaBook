package ar.edu.uner.prestabook.connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {

    /**
     * The main runtime interface between a Java application and Hibernate. This is
     * thecentral API class abstracting the notion of a persistence service.
     */
    private static Session currentSession;

    /**
     * Defines the contract for abstracting applications from the configured
     * underlying means of transaction management.
     */
    private static Transaction currentTransaction;

    /**
     * Open a Session.
     * 
     * @return A Session
     */
    public static Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    /**
     * Open a Session with the current transaction.
     * 
     * @return A Session
     */
    public static Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    /**
     * End the session by releasing the JDBC connection and cleaning up.
     */
    public static void closeCurrentSession() {
        currentSession.close();
    }

    /**
     * Commit the current resource transaction, writing any unflushed changes to the
     * database and ends the session.
     * 
     */
    public static void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    /**
     * Create a SessionFactory using the properties and mappings in the
     * configuration.
     * 
     * @return a SessionFactory
     */
    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().configure();
        return configuration.buildSessionFactory(builder.build());
    }

    /**
     * Returns the current session. If it is closed, it opens it.
     * 
     * @return a Session
     */
    public static Session getCurrentSession() {
        if (!currentSession.isConnected()) {
            openCurrentSession();
        }
        return currentSession;
    }

    /**
     * Sets the current session to the given parameter
     * 
     * @param currentSession a Session
     */
    public static void setCurrentSession(Session currentSession) {
        HibernateConnection.currentSession = currentSession;
    }

    /**
     * Returns the current transaction
     * 
     * @return current transaction
     */
    public static Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    /**
     * Sets the current transaction to the given parameter
     * 
     * @param currentTransaction a Transaction
     */
    public static void setCurrentTransaction(Transaction currentTransaction) {
        HibernateConnection.currentTransaction = currentTransaction;
    }

}

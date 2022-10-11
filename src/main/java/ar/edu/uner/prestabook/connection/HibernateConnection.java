package ar.edu.uner.prestabook.connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {

    private static Session currentSession;

    private static Transaction currentTransaction;

    public static Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public static Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public static void closeCurrentSession() {
        currentSession.close();
    }

    public static void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().configure();
        return configuration.buildSessionFactory(builder.build());
    }

    public static Session getCurrentSession() {
        if (!currentSession.isConnected()) {
            openCurrentSession();
        }
        return currentSession;
    }

    public static void setCurrentSession(Session currentSession) {
        HibernateConnection.currentSession = currentSession;
    }

    public static Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public static void setCurrentTransaction(Transaction currentTransaction) {
        HibernateConnection.currentTransaction = currentTransaction;
    }

}

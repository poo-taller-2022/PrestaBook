package ar.edu.uner.prestabook.persistence.impl;

import java.util.List;

import org.hibernate.Transaction;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.model.Alumno;
import ar.edu.uner.prestabook.persistence.IAlumnoDAO;

public class AlumnoDAO implements IAlumnoDAO {

    /**
     * Singleton instance of the class
     */
    private static final AlumnoDAO instance = new AlumnoDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private AlumnoDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static AlumnoDAO getInstance() {
        return instance;
    }

    @Override
    public List<Alumno> findAll() {
        return HibernateConnection.getCurrentSession().createQuery("from Alumno", Alumno.class).list();

    }

    @Override
    public Alumno findById(Object dni) {
        return HibernateConnection.getCurrentSession().get(Alumno.class, (String) dni);

    }

    @Override
    public Alumno insert(Alumno alumno) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        Long documento = (Long) HibernateConnection.getCurrentSession().save(alumno);
        tx.commit();
        alumno.setDocumento(documento);
        return alumno;
    }

    @Override
    public Alumno update(Alumno alumno) {
        Transaction tx = HibernateConnection.getCurrentSession().beginTransaction();
        HibernateConnection.getCurrentSession().update(alumno);
        tx.commit();
        return alumno;
    }

    @Override
    public Integer delete(Alumno alumno) {
        return 0;
    }

}

package ar.edu.uner.prestabook.persistence;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Alumno;
import ar.edu.uner.prestabook.model.Lector;

public interface IAlumnoDAO extends GenericDAO<Alumno> {

    public Alumno findByEmail(String email);

}
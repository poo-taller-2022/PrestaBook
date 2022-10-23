package ar.edu.uner.prestabook.persistence;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Docente;
import ar.edu.uner.prestabook.model.Lector;

public interface IDocenteDAO extends GenericDAO<Docente> {

    public Docente findByEmail(String email);

}
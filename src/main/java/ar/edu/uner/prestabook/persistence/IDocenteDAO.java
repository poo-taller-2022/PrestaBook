package ar.edu.uner.prestabook.persistence;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Docente;

/**
 * 
 * Interface defining the contract for the bussiness logic of Docente
 *
 */

public interface IDocenteDAO extends GenericDAO<Docente> {

    public Docente findByEmail(String email);

}
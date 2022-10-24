package ar.edu.uner.prestabook.persistence;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Alumno;

public interface IAlumnoDAO extends GenericDAO<Alumno> {

	
	/**
	 * Finds an alumn by his email
	 * @param email String that represent the email to search
	 * @return entity of type Alumno if the email matches the database field, null if it does not
	 */
    public Alumno findByEmail(String email);

}
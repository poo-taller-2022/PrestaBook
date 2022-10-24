package ar.edu.uner.prestabook.persistence;

import java.util.List;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Multa;

public interface IMultaDAO extends GenericDAO<Multa> {

	
	/**
	 * Finds an entity by its documentoLector
	 * @param documentoLector String that represent the documento to search
	 * @return List with all entities of type Multa if the documentoLector matches the database field, null if it does not
	 */
    List<Multa> findByAllDocumentoLector(Long documentoLector);

}

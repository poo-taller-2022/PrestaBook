package ar.edu.uner.prestabook.persistence;

import java.util.List;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Edicion;


/**
 * 
 * Interface defining the contract for the bussiness logic of Edicion
 *
 */
public interface IEdicionDAO extends GenericDAO<Edicion> {

	
	/**
	 * Finds an Obra by its obra isbn
	 * @param isbn String that represent the isbn to search
	 * @return List with all entities of type Edicion if the isbn matches the database field, null if it does not
	 */
	List<Edicion> findByAllObraIsbn(String isbn);

}

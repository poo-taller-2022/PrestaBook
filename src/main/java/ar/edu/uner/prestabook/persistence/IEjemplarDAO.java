package ar.edu.uner.prestabook.persistence;

import java.util.List;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Ejemplar;

public interface IEjemplarDAO extends GenericDAO<Ejemplar> {

	/**
	 * Fetches a list, filtered by their isbn
	 * @param isbn 
	 * @return A list with all the copies of the book
	 */
	List<Ejemplar> findAllByObraIsbn(String isbn);
	
}

package ar.edu.uner.prestabook.persistence;

import java.util.List;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Ejemplar;

public interface IEjemplarDAO extends GenericDAO<Ejemplar> {

	/**
	 * Trae una lista de todos los ejemplares, filtrados por el isbn
	 * @param isbn de la obra
	 * @return Una lista con los ejemplares encontrados
	 */
	List<Ejemplar> findAllByObraIsbn(String isbn);
	
}

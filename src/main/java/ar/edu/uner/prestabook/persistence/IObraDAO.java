package ar.edu.uner.prestabook.persistence;

import java.util.List;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Obra;


/**
 * 
 * Interface defining the contract for the bussiness logic of Obra
 *
 */
public interface IObraDAO extends GenericDAO<Obra> {

	
    
    /**
     * Finds all the obras filtered by the id of a tema 
     * @param id String that represents the id of tema
     * @return List with all entities the type Obra 
     */
	List<Obra> filtrarPorTema(Integer id);

}

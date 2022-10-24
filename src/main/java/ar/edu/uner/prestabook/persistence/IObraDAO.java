package ar.edu.uner.prestabook.persistence;

import java.util.List;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Obra;

public interface IObraDAO extends GenericDAO<Obra> {

	
    
    /**
     * Finds all entities from table obras where obras.id matches with the given id 
     * @param id String that represents the id of area
     * @return List with all entities the type Obra 
     */
	List<Obra> filtrarPorTema(Integer id);

}

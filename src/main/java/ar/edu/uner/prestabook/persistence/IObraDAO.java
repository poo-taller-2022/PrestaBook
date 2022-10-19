package ar.edu.uner.prestabook.persistence;

import java.util.List;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Obra;

public interface IObraDAO extends GenericDAO<Obra> {

	
    
    /**
     * Finds all entities from table obras where obras.id matches with the given id 
     * @return list of objects Obra
     * @param id of area
     */
	List<Obra> filtrarPorTema(Integer id);

}

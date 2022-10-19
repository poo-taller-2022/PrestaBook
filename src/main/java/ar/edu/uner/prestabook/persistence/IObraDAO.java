package ar.edu.uner.prestabook.persistence;

import java.util.List;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Obra;

public interface IObraDAO extends GenericDAO<Obra> {

	
    
    /**
     * Find all entities from table obras where obras.id match with id 
     * @return list of objects Obra
     * @param id of Obra
     */
	List<Obra> filtrarPorTema(Integer id);

}

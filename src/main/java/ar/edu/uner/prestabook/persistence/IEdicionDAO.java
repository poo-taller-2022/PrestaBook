package ar.edu.uner.prestabook.persistence;

import java.util.List;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Edicion;

public interface IEdicionDAO extends GenericDAO<Edicion> {

	List<Edicion> findByEditorial(String editorial);

}

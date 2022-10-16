package ar.edu.uner.prestabook.persistence;

import java.util.List;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Obra;

public interface IObraDAO extends GenericDAO<Obra> {

	List<Obra> filtrarPorTema(Integer id);

}

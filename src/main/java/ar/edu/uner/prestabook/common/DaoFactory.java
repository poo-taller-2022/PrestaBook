package ar.edu.uner.prestabook.common;

import ar.edu.uner.prestabook.persistence.AreaTematicaDAO;
import ar.edu.uner.prestabook.persistence.IAreaTematicaDAO;

public class DaoFactory {

	private DaoFactory() {
	}

	public static IAreaTematicaDAO getAreaTematicaDAO() {
		return new AreaTematicaDAO();
	}

}

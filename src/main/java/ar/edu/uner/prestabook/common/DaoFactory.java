package ar.edu.uner.prestabook.common;

import java.sql.Connection;

import ar.edu.uner.prestabook.persistence.AreaTematicaDAO;
import ar.edu.uner.prestabook.persistence.IAreaTematicaDAO;

public class DaoFactory {

	private DaoFactory() {
	}

	public static IAreaTematicaDAO getAreaTematicaDAO(Connection conn) {
		return new AreaTematicaDAO(conn);
	}

}

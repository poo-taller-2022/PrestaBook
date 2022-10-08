package ar.edu.uner.prestabook.common;

import ar.edu.uner.prestabook.persistence.IAlumnoDAO;
import ar.edu.uner.prestabook.persistence.IAreaTematicaDAO;
import ar.edu.uner.prestabook.persistence.IDocenteDAO;
import ar.edu.uner.prestabook.persistence.IEjemplarDAO;
import ar.edu.uner.prestabook.persistence.ILectorDAO;
import ar.edu.uner.prestabook.persistence.IObraDAO;
import ar.edu.uner.prestabook.persistence.ITipoObraDAO;
import ar.edu.uner.prestabook.persistence.impl.AlumnoDAO;
import ar.edu.uner.prestabook.persistence.impl.AreaTematicaDAO;
import ar.edu.uner.prestabook.persistence.impl.DocenteDAO;
import ar.edu.uner.prestabook.persistence.impl.EjemplarDAO;
import ar.edu.uner.prestabook.persistence.impl.LectorDAO;
import ar.edu.uner.prestabook.persistence.impl.ObraDAO;
import ar.edu.uner.prestabook.persistence.impl.TipoObraDAO;

/**
 * DAO Factory designed to return DAO instances
 *
 */
public class DaoFactory {

	/**
	 * Private constructor to avoid instantiation
	 */
	private DaoFactory() {
	}

	/**
	 * Returns an instance of an Area Tematica DAO
	 * 
	 * @return an Area Tematica DAO
	 */
	public static IAreaTematicaDAO getAreaTematicaDAO() {
		return AreaTematicaDAO.getInstance();
	}

	/**
	 * Returns an instance of an Alumno DAO
	 * 
	 * @return an Alumno DAO
	 */
	public static IAlumnoDAO getAlumnoDAO() {
		return AlumnoDAO.getInstance();
	}

	/**
	 * Returns an instance of a Docente DAO
	 * 
	 * @return a Docente DAO
	 */
	public static IDocenteDAO getDocenteDAO() {
		return DocenteDAO.getInstance();
	}

	/**
	 * Returns an instance of a Lector DAO
	 * 
	 * @return a Lector DAO
	 */
	public static ILectorDAO getLectorDAO() {
		return LectorDAO.getInstance();
	}

	/**
	 * Returns an instance of an Obra DAO
	 * 
	 * @return an Obra DAO
	 */
	public static IObraDAO getObraDAO() {
		return ObraDAO.getInstance();
	}

	/**
	 * Returns an instance of a Tipo Obra DAO
	 * 
	 * @return a Tipo Obra DAO
	 */
	public static ITipoObraDAO getTipoObraDAO() {
		return TipoObraDAO.getInstance();
	}

	/**
	 * Returns an instance of an Ejemplar DAO
	 * 
	 * @return an Ejemplar DAO
	 */
	public static IEjemplarDAO getEjemplarDAO() {
		return EjemplarDAO.getInstance();
	}

}

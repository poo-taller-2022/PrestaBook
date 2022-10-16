package ar.edu.uner.prestabook.common;

import ar.edu.uner.prestabook.persistence.IAlumnoDAO;
import ar.edu.uner.prestabook.persistence.IAreaTematicaDAO;
import ar.edu.uner.prestabook.persistence.ICodigoIdentificatorioDAO;
import ar.edu.uner.prestabook.persistence.IColeccionDAO;
import ar.edu.uner.prestabook.persistence.IDocenteDAO;
import ar.edu.uner.prestabook.persistence.IEdicionDAO;
import ar.edu.uner.prestabook.persistence.IEjemplarDAO;
import ar.edu.uner.prestabook.persistence.IFormatoDAO;
import ar.edu.uner.prestabook.persistence.IFuncionarioDAO;
import ar.edu.uner.prestabook.persistence.ILectorDAO;
import ar.edu.uner.prestabook.persistence.IObraDAO;
import ar.edu.uner.prestabook.persistence.IPrestamoDAO;
import ar.edu.uner.prestabook.persistence.ITipoObraDAO;
import ar.edu.uner.prestabook.persistence.IUsuarioDAO;
import ar.edu.uner.prestabook.persistence.impl.AlumnoDAO;
import ar.edu.uner.prestabook.persistence.impl.AreaTematicaDAO;
import ar.edu.uner.prestabook.persistence.impl.CodigoIdentificatorioDAO;
import ar.edu.uner.prestabook.persistence.impl.ColeccionDAO;
import ar.edu.uner.prestabook.persistence.impl.DocenteDAO;
import ar.edu.uner.prestabook.persistence.impl.EdicionDAO;
import ar.edu.uner.prestabook.persistence.impl.EjemplarDAO;
import ar.edu.uner.prestabook.persistence.impl.FormatoDAO;
import ar.edu.uner.prestabook.persistence.impl.FuncionarioDAO;
import ar.edu.uner.prestabook.persistence.impl.LectorDAO;
import ar.edu.uner.prestabook.persistence.impl.ObraDAO;
import ar.edu.uner.prestabook.persistence.impl.PrestamoDAO;
import ar.edu.uner.prestabook.persistence.impl.TipoObraDAO;
import ar.edu.uner.prestabook.persistence.impl.UsuarioDAO;

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

    /**
     * Returns an instance of a Formato DAO
     *
     * @return a Formato DAO
     */
    public static IFormatoDAO getFormatoDAO() {
        return FormatoDAO.getInstance();
    }

    /**
     * Returns an instance of a Funcionario DAO
     *
     * @return a Funcionario DAO
     */
    public static IFuncionarioDAO getFuncionarioDAO() {
        return FuncionarioDAO.getInstance();
    }

    /**
     * Returns an instance of a Usuario DAO
     *
     * @return an Usuario DAO
     */
    public static IUsuarioDAO getUsuarioDAO() {
        return UsuarioDAO.getInstance();
    }

    /**
     * Returns an instance of a Coleccion DAO
     *
     * @return a Coleccion DAO
     */
    public static IColeccionDAO getColeccionDAO() {
        return ColeccionDAO.getInstance();
    }

    
    /**
     * Returns an instance of a Edicion DAO
     * 
     * @return an Edicion DAO
     */
    public static IEdicionDAO getEdicionDAO() {
        return EdicionDAO.getInstance();
    }


    /**
     * Returns an instance of a Prestamo DAO
     *
     * @return a Prestamo DAO
     */
    public static IPrestamoDAO getPrestamoDAO() {
        return PrestamoDAO.getInstance();
    }
    /**
     * Returns an instance of a CodigoIdentificatorio DAO
     *
     * @return a CodigoIdentificatorio DAO
     */
    public static ICodigoIdentificatorioDAO getCodigoIdentificatorioDAO() {
        return CodigoIdentificatorioDAO.getInstance();
    }

}

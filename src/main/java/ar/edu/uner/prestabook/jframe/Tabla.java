package ar.edu.uner.prestabook.jframe;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.model.AreaTematica;
import ar.edu.uner.prestabook.model.Coleccion;
import ar.edu.uner.prestabook.model.Edicion;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.model.Formato;
import ar.edu.uner.prestabook.model.Lector;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.model.TipoObra;
import ar.edu.uner.prestabook.persistence.IAreaTematicaDAO;
import ar.edu.uner.prestabook.persistence.IColeccionDAO;
import ar.edu.uner.prestabook.persistence.IEdicionDAO;
import ar.edu.uner.prestabook.persistence.IEjemplarDAO;
import ar.edu.uner.prestabook.persistence.IFormatoDAO;
import ar.edu.uner.prestabook.persistence.IObraDAO;
import ar.edu.uner.prestabook.persistence.ITipoObraDAO;

public class Tabla {

    private Tabla() {
    }

    public static void fill(DefaultTableModel model, String tipoEntidad) {
        Integer i = 0;
        switch (tipoEntidad) {
            case Constants.TIPO_OBRA:
                loadTipoObra(model, i);
                break;
            case Constants.AREA_TEMATICA:
                loadAreaTematica(model, i);
                break;
            case Constants.FORMATO:
                loadFormato(model, i);
                break;
            case Constants.COLECCION:
                loadColeccion(model, i);
                break;
            case Constants.OBRA:
                loadObra(model, i);
                break;
            case Constants.EJEMPLAR:
                loadEjemplar(model, i);
                break;
            case Constants.EDICION:
                loadEdicion(model, i);
                break;
            case Constants.LECTOR:
                loadLector(model, i);
                break;
            default:
        }
    }

    private static void loadEdicion(DefaultTableModel model, Integer i) {
        IEdicionDAO edicionDAO = DaoFactory.getEdicionDAO();
        List<Edicion> ediciones = edicionDAO.findAll();
        for (Edicion edicion : ediciones) {
            StringBuilder concatenarFormatos = new StringBuilder();
            List<Object> fila = new LinkedList<>();
            fila.add(++i);
            fila.add(edicion.getEditorial());
            fila.add(edicion.getPais());
            fila.add(edicion.getNumero());
            fila.add(edicion.getAnio());
            fila.add(edicion.getVolumenes());
            fila.add(edicion.getPaginas());
            fila.add(edicion.getIdioma());
            Set<Formato> formatos1 = edicion.getFormatos();
            for (Formato formato : formatos1) {
                concatenarFormatos.append(formato.getNombre() + ", ");
            }
            fila.add(concatenarFormatos.toString());
            fila.add(edicion.getFormatos());
            model.addRow(new Vector<>(fila));
        }
    }

    private static void loadEjemplar(DefaultTableModel model, Integer i) {
        IEjemplarDAO ejemplarDAO = DaoFactory.getEjemplarDAO();
        List<Ejemplar> ejemplares = ejemplarDAO.findAll();
        for (Ejemplar ejemplar : ejemplares) {
            List<Object> fila = new LinkedList<>();
            fila.add(++i);
            fila.add(ejemplar.getIsbnObra());
            fila.add(ejemplar.getFormaAdquisicion());
            fila.add(ejemplar.getFechaAdquisicion());
            fila.add(ejemplar.getObservaciones());
            fila.add(ejemplar.getCodigoIdentificatorio().getCodigo());
            fila.add(ejemplar.getCodigoIdentificatorio().getPasillo());
            fila.add(ejemplar.getCodigoIdentificatorio().getEstanteria());
            fila.add(ejemplar.getCodigoIdentificatorio().getEstante());
            model.addRow(new Vector<>(fila));
        }
    }

    private static void loadObra(DefaultTableModel model, Integer i) {
        IObraDAO obraDAO = DaoFactory.getObraDAO();
        List<Obra> obras = obraDAO.findAll();
        for (Obra obra : obras) {
            List<Object> fila = new LinkedList<>();
            fila.add(++i);
            fila.add(obra.getIsbn());
            fila.add(obra.getTitulo());
            fila.add(obra.getSubtitulo());
            fila.add(obra.getPrimerAutor());
            fila.add(obra.getSegundoAutor());
            fila.add(obra.getTercerAutor());
            fila.add(obra.getGenero());
            fila.add(obra.getTipo().getNombre());
            fila.add(obra.getArea().getNombre());
            model.addRow(new Vector<>(fila));
        }
    }

    private static void loadColeccion(DefaultTableModel model, Integer i) {
        IColeccionDAO coleccionDAO = DaoFactory.getColeccionDAO();
        List<Coleccion> colecciones = coleccionDAO.findAll();
        for (Coleccion coleccion : colecciones) {
            List<Object> fila = new LinkedList<>();
            fila.add(++i);
            fila.add(coleccion.getIsbn());
            fila.add(coleccion.getTitulo());
            fila.add(coleccion.getSubtitulo());
            fila.add(coleccion.getPrimerAutor());
            fila.add(coleccion.getSegundoAutor());
            fila.add(coleccion.getTercerAutor());
            fila.add(coleccion.getGenero());
            fila.add(coleccion.getTipo().getNombre());
            fila.add(coleccion.getArea().getNombre());
            model.addRow(new Vector<>(fila));
        }
    }

    private static void loadFormato(DefaultTableModel model, Integer i) {
        IFormatoDAO formatoDAO = DaoFactory.getFormatoDAO();
        List<Formato> formatos = formatoDAO.findAll();
        for (Formato formato : formatos) {
            List<Object> fila = new LinkedList<>();
            fila.add(++i);
            fila.add(formato.getNombre());
            model.addRow(new Vector<>(fila));
        }
    }

    private static void loadAreaTematica(DefaultTableModel model, Integer i) {
        IAreaTematicaDAO areaTematicaDAO = DaoFactory.getAreaTematicaDAO();
        List<AreaTematica> areasTematicas = areaTematicaDAO.findAll();
        for (AreaTematica area : areasTematicas) {
            List<Object> fila = new LinkedList<>();
            fila.add(++i);
            fila.add(area.getNombre());
            model.addRow(new Vector<>(fila));
        }
    }

    private static void loadTipoObra(DefaultTableModel model, Integer i) {
        ITipoObraDAO tipoObraDAO = DaoFactory.getTipoObraDAO();
        List<TipoObra> tiposObra = tipoObraDAO.findAll();
        for (TipoObra tipo : tiposObra) {
            List<Object> fila = new LinkedList<>();
            fila.add(++i);
            fila.add(tipo.getNombre());
            model.addRow(new Vector<>(fila));
        }
    }

    private static void loadLector(DefaultTableModel model, Integer i) {
        List<Lector> lectores = DaoFactory.getLectorDAO().findAll();
        for (Lector lector : lectores) {
            List<Object> fila = new LinkedList<>();
            fila.add(++i);
            fila.add(lector.getNombre());
            fila.add(lector.getApellido());
            fila.add(lector.getEmail());
            fila.add(lector.getDomicilio());
            fila.add(lector.getFechaNacimiento());
            fila.add(DaoFactory.getLectorDAO().countFinesById(lector.getDocumento()));
            model.addRow(new Vector<>(fila));
        }
    }

}

package ar.edu.uner.prestabook.jframe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import ar.edu.uner.prestabook.model.Multa;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.model.Prestamo;
import ar.edu.uner.prestabook.model.Reserva;
import ar.edu.uner.prestabook.model.TipoObra;
import ar.edu.uner.prestabook.persistence.IAreaTematicaDAO;
import ar.edu.uner.prestabook.persistence.IColeccionDAO;
import ar.edu.uner.prestabook.persistence.IEdicionDAO;
import ar.edu.uner.prestabook.persistence.IEjemplarDAO;
import ar.edu.uner.prestabook.persistence.IFormatoDAO;
import ar.edu.uner.prestabook.persistence.IObraDAO;
import ar.edu.uner.prestabook.persistence.IPrestamoDAO;
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
            case Constants.MULTAS:
                loadMultas(model, i);
                break;
            case Constants.OBRAS_POR_EDITORIAL:
                loadObrasPorEditorial(model, i);
                break;
            case Constants.PRESTAMOS:
                loadPrestamos(model, i);
                break;
            case Constants.EJEMPLARES_POR_AREA:
                loadEjemplaresPorArea(model, i);
                break;
            case Constants.OBRAS_POR_AREA:
                loadObrasPorArea(model, i);
                break;
            case Constants.OBRAS_LECTOR_VIEW:
                loadObrasLector(model, i);
                break;
            case Constants.RESERVAS:
                loadReservas(model);
                break;
            case Constants.OBRAS_MAS_SOLICITADAS:
                loadObrasMasSolicitadas(model, i);
                break;
            default:
        }
    }

    private static void loadObrasPorEditorial(DefaultTableModel model, Integer i) {

        IEdicionDAO edicionDAO = DaoFactory.getEdicionDAO();
        List<Edicion> ediciones = edicionDAO.findAll();
        for (Edicion edicion : ediciones) {
            List<Object> fila = new LinkedList<>();

            Obra obra = DaoFactory.getObraDAO().findById(edicion.getIsbnObra());
            fila.add(++i);
            fila.add(edicion.getEditorial().toUpperCase());
            fila.add(obra.getIsbn().toUpperCase());
            fila.add(obra.getTitulo().toUpperCase());
            fila.add(obra.getSubtitulo().toUpperCase());
            fila.add(obra.getPrimerAutor().toUpperCase());
            fila.add(obra.getSegundoAutor().toUpperCase());
            fila.add(obra.getTercerAutor().toUpperCase());
            fila.add(obra.getGenero().toUpperCase());
            fila.add(obra.getTipo().getNombre().toUpperCase());

            Set<AreaTematica> areas = obra.getArea();
            StringBuilder contatenarAreas = new StringBuilder();

            for (AreaTematica area : areas) {
                contatenarAreas.append(area.getNombre().toUpperCase() + ", ");
            }
            contatenarAreas = contatenarAreas.deleteCharAt(contatenarAreas.length() - 2);

            fila.add(contatenarAreas);

            model.addRow(new Vector<>(fila));
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
            concatenarFormatos = concatenarFormatos.deleteCharAt(concatenarFormatos.length() - 2);
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
            StringBuilder concatenarAreasTematicas = new StringBuilder();
            fila.add(++i);
            fila.add(obra.getIsbn());
            fila.add(obra.getTitulo());
            fila.add(obra.getSubtitulo());
            fila.add(obra.getPrimerAutor());
            fila.add(obra.getSegundoAutor());
            fila.add(obra.getTercerAutor());
            fila.add(obra.getGenero());
            fila.add(obra.getTipo().getNombre());
            Set<AreaTematica> areasTematicas = obra.getArea();
            for (AreaTematica area : areasTematicas) {
                concatenarAreasTematicas.append(area.getNombre() + ", ");
            }
            concatenarAreasTematicas = concatenarAreasTematicas.deleteCharAt(concatenarAreasTematicas.length() - 2);
            fila.add(concatenarAreasTematicas);
            model.addRow(new Vector<>(fila));
        }
    }

    private static void loadColeccion(DefaultTableModel model, Integer i) {
        IColeccionDAO coleccionDAO = DaoFactory.getColeccionDAO();
        List<Coleccion> colecciones = coleccionDAO.findAll();
        for (Coleccion coleccion : colecciones) {
            List<Object> fila = new LinkedList<>();
            StringBuilder concatenarAreasTematicas = new StringBuilder();
            fila.add(++i);
            fila.add(coleccion.getIsbn());
            fila.add(coleccion.getTitulo());
            fila.add(coleccion.getSubtitulo());
            fila.add(coleccion.getPrimerAutor());
            fila.add(coleccion.getSegundoAutor());
            fila.add(coleccion.getTercerAutor());
            fila.add(coleccion.getGenero());
            fila.add(coleccion.getTipo().getNombre());
            Set<AreaTematica> areasTematicas = coleccion.getArea();
            for (AreaTematica area : areasTematicas) {
                concatenarAreasTematicas.append(area.getNombre() + ", ");
            }
            fila.add(concatenarAreasTematicas);
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

    private static void loadMultas(DefaultTableModel model, Integer i) {
        List<Multa> multas = DaoFactory.getMultaDAO().findAll();
        for (Multa multa : multas) {
            List<Object> fila = new LinkedList<>();
            fila.add(++i);
            fila.add(LocalDate.parse(multa.getFecha(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            fila.add(multa.getPlazo());
            fila.add(multa.getLector().getNombre());
            fila.add(multa.getLector().getApellido());
            model.addRow(new Vector<>(fila));
        }
    }

    private static void loadReservas(DefaultTableModel model) {
        List<Reserva> reservas = DaoFactory.getReservaDAO().findAll();
        for (Reserva reserva : reservas) {
            if (Boolean.TRUE.equals(reserva.getIsActive())) {
                List<Object> fila = new LinkedList<>();
                Obra obra = DaoFactory.getObraDAO().findById(reserva.getEjemplar().getIsbnObra());
                fila.add(reserva.getId());
                fila.add(String.format("%s %s", reserva.getLector().getNombre(), reserva.getLector().getApellido()));
                fila.add(obra.getTitulo());
                fila.add(reserva.getEjemplar().getId());
                fila.add(LocalDate.parse(reserva.getFechaReserva(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                model.addRow(new Vector<>(fila));
            }
        }
    }

    private static void loadPrestamos(DefaultTableModel model, Integer i) {
        List<Prestamo> prestamos = DaoFactory.getPrestamoDAO().findAll();
        for (Prestamo prestamo : prestamos) {
            LocalDateTime fechaPrestamo = LocalDateTime.parse(prestamo.getFechaYHoraPrestamo());
            LocalDate fechaPactadaDevolucion = LocalDate.parse(prestamo.getFechaPactadaDevolucion(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate fechaRealDevolucion = prestamo.getFechaRealDevolucion() != null
                    ? LocalDate.parse(prestamo.getFechaRealDevolucion(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    : null;
            Boolean fueraDeTermino = fechaPrestamo.plusDays(4).toLocalDate()
                    .isBefore(fechaRealDevolucion != null ? fechaRealDevolucion : fechaPactadaDevolucion);

            List<Object> fila = new LinkedList<>();
            fila.add(++i);
            fila.add(DaoFactory.getObraDAO().findById(prestamo.getEjemplar().getIsbnObra()).getTitulo());
            fila.add(prestamo.getEjemplar().getId());
            fila.add(fechaPrestamo);
            fila.add(fechaPactadaDevolucion);
            fila.add(fechaRealDevolucion);
            fila.add(prestamo.getLector().getNombre());
            fila.add(prestamo.getLector().getApellido());
            fila.add(Boolean.TRUE.equals(fueraDeTermino) ? "Sí" : "No");
            model.addRow(new Vector<>(fila));
        }
    }

    private static void loadEjemplaresPorArea(DefaultTableModel model, Integer i) {
        IEjemplarDAO ejemplarDAO = DaoFactory.getEjemplarDAO();
        IPrestamoDAO prestamoDAO = DaoFactory.getPrestamoDAO();
        IObraDAO obraDAO = DaoFactory.getObraDAO();

        List<Ejemplar> ejemplares = ejemplarDAO.findAll();

        if (ejemplares != null) {
            for (Ejemplar ejemplar : ejemplares) {
                if (ejemplar.getMotivoBaja() == null && prestamoDAO.findAllByIdEjemplar(ejemplar.getId()).isEmpty()) {
                    List<Object> fila = new LinkedList<>();
                    fila.add(++i);

                    Obra obra = obraDAO.findById(ejemplar.getIsbnObra());

                    Set<AreaTematica> areas = obra.getArea();
                    StringBuilder contatenarAreas = new StringBuilder();

                    for (AreaTematica area : areas) {
                        contatenarAreas.append(area.getNombre().toUpperCase() + ", ");
                    }
                    contatenarAreas = contatenarAreas.deleteCharAt(contatenarAreas.length() - 2);
                    fila.add(contatenarAreas);
                    fila.add(ejemplar.getIsbnObra().toUpperCase());
                    fila.add(ejemplar.getTitulo().toUpperCase());
                    fila.add(ejemplar.getSubtitulo().toUpperCase());
                    fila.add(ejemplar.getPrimerAutor().toUpperCase());
                    fila.add(ejemplar.getSegundoAutor().toUpperCase());
                    fila.add(ejemplar.getTercerAutor().toUpperCase());
                    fila.add(ejemplar.getGenero().toUpperCase());
                    fila.add(ejemplar.getTipo());
                    fila.add(ejemplar.getFormaAdquisicion().toUpperCase());
                    fila.add(ejemplar.getFechaAdquisicion().toUpperCase());
                    fila.add(ejemplar.getObservaciones().toUpperCase());
                    fila.add(ejemplar.getCodigoIdentificatorio());
                    model.addRow(new Vector<>(fila));
                }
            }
        }
    }

    /**
     * 
     */

    public static void loadObrasPorArea(DefaultTableModel model, Integer i) {

        IObraDAO obraDAO = DaoFactory.getObraDAO();
        List<Obra> obras = obraDAO.findAll();
        for (Obra obra : obras) {
            List<Object> fila = new LinkedList<>();
            fila.add(++i);

            Set<AreaTematica> areas = obra.getArea();
            StringBuilder contatenarAreas = new StringBuilder();

            for (AreaTematica area : areas) {
                contatenarAreas.append(area.getNombre().toUpperCase() + ", ");
            }
            contatenarAreas = contatenarAreas.deleteCharAt(contatenarAreas.length() - 2);
            fila.add(contatenarAreas);

            fila.add(obra.getIsbn().toUpperCase());
            fila.add(obra.getTitulo().toUpperCase());
            fila.add(obra.getSubtitulo().toUpperCase());
            fila.add(obra.getPrimerAutor().toUpperCase());
            fila.add(obra.getGenero().toUpperCase());
            fila.add(obra.getTipo().getNombre().toUpperCase());

            model.addRow(new Vector<>(fila));
        }
    }

    private static void loadObrasLector(DefaultTableModel model, Integer i) {

        List<Obra> obras = DaoFactory.getObraDAO().findAll();
        for (Obra obra : obras) {
            Set<AreaTematica> areas = obra.getArea();
            StringBuilder contatenarAreas = new StringBuilder();
            for (AreaTematica area : areas) {
                contatenarAreas.append(area.getNombre().toUpperCase() + ", ");
            }
            contatenarAreas = contatenarAreas.deleteCharAt(contatenarAreas.length() - 2);

            List<Object> fila = new LinkedList<>();
            fila.add(++i);
            fila.add(contatenarAreas);
            fila.add(obra.getIsbn().toUpperCase());
            fila.add(obra.getTitulo().toUpperCase());
            fila.add(obra.getSubtitulo().toUpperCase());
            fila.add(obra.getPrimerAutor().toUpperCase());
            fila.add(obra.getGenero().toUpperCase());
            fila.add(obra.getTipo().getNombre().toUpperCase());

            List<Ejemplar> ejemplares = DaoFactory.getEjemplarDAO().findAllByObraIsbn(obra.getIsbn());
            Integer cantidadEjemplares = 0;
            if (ejemplares != null) {
                for (Ejemplar ejemplar : ejemplares) {
                    if (ejemplar.getMotivoBaja() == null) {
                        cantidadEjemplares++;
                    }
                }
                fila.add(cantidadEjemplares);
            } else {
                fila.add(cantidadEjemplares);
            }
            model.addRow(new Vector<>(fila));
        }
    }

    private static void loadObrasMasSolicitadas(DefaultTableModel model, Integer i) {

        List<Obra> obras = DaoFactory.getObraDAO().findAll();

        for (Obra obra : obras) {
            List<Object> fila = new LinkedList<>();
            fila.add(++i);
            fila.add(obra.getIsbn().toUpperCase());
            fila.add(obra.getTitulo().toUpperCase());
            fila.add(obra.getPrimerAutor().toUpperCase());
            fila.add(obra.getGenero().toUpperCase());
            fila.add(obra.getTipo().getNombre().toUpperCase());
            fila.add(DaoFactory.getReservaDAO().countByObraIsbn(obra.getIsbn()));
            fila.add(DaoFactory.getPrestamoDAO().countByObraIsbn(obra.getIsbn()));
            model.addRow(new Vector<>(fila));
        }
    }
}

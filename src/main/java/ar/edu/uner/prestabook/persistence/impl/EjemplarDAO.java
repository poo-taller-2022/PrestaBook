package ar.edu.uner.prestabook.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ar.edu.uner.prestabook.connection.ConnectionProvider;
import ar.edu.uner.prestabook.model.AreaTematica;
import ar.edu.uner.prestabook.model.CodigoIdentificatorio;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.model.TipoObra;
import ar.edu.uner.prestabook.persistence.IEjemplarDAO;

public class EjemplarDAO implements IEjemplarDAO {

    /**
     * Singleton instance of the class
     */
    private static final EjemplarDAO instance = new EjemplarDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private EjemplarDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static EjemplarDAO getInstance() {
        return instance;
    }

    @Override
    public List<Ejemplar> findAll() {
        String sql = "SELECT ejemplares.*,"
                + "obras.*,"
                + "codigos_identificatorios.*,"
                + "tipos_obra.id as 'tipo_id',"
                + "tipos_obra.nombre as 'tipo_nombre',"
                + "areas_tematicas.id as 'area_id',"
                + "areas_tematicas.nombre as 'area_nombre' "
                + "FROM EJEMPLARES "
                + "INNER JOIN codigos_identificatorios ON ejemplares.codigo_identificatorio = codigos_identificatorios.codigo "
                + "INNER JOIN obras ON ejemplares.isbn = obras.isbn "
                + "INNER JOIN tipos_obra ON obras.tipo_obra = tipos_obra.id "
                + "INNER JOIN areas_tematicas ON obras.area_tematica = areas_tematicas.id";
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            List<Ejemplar> ejemplares = new LinkedList<>();
            while (resultados.next()) {
                ejemplares.add(toEjemplar(resultados));
            }
            return ejemplares;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Ejemplar findById(Object isbn) {
        String sql = String.format("SELECT ejemplares.*,"
                + "obras.*,"
                + "codigos_identificatorios.*,"
                + "tipos_obra.id as 'tipo_id',"
                + "tipos_obra.nombre as 'tipo_nombre',"
                + "areas_tematicas.id as 'area_id',"
                + "areas_tematicas.nombre as 'area_nombre' "
                + "FROM EJEMPLARES "
                + "INNER JOIN codigos_identificatorios ON ejemplares.codigo_identificatorio = codigos_identificatorios.codigo "
                + "INNER JOIN obras ON ejemplares.isbn = obras.isbn "
                + "INNER JOIN tipos_obra ON obras.tipo_obra = tipos_obra.id "
                + "INNER JOIN areas_tematicas ON obras.area_tematica = areas_tematicas.id WHERE ISBN = %s",
                isbn.toString());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            Ejemplar ejemplar = null;
            if (resultados.next()) {
                ejemplar = toEjemplar(resultados);
            }
            return ejemplar;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer insert(Ejemplar ejemplar) {
        String sql = String.format(
                "INSERT INTO EJEMPLARES (ISBN, FORMA_ADQUISICION, FECHA_ADQUISICION, OBSERVACIONES, FECHA_BAJA, MOTIVO_BAJA, UBICACION) VALUES ('%s','%s','%s','%s','%s','%s', '%s')",
                ejemplar.getIsbn(), ejemplar.getFormaAdquisicion(), ejemplar.getFechaAdquisicion(),
                ejemplar.getObservaciones(), ejemplar.getFechaBaja(), ejemplar.getMotivoBaja(),
                ejemplar.getUbicacion());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer update(Ejemplar ejemplar) {
        String sql = String.format(
                "UPDATE EJEMPLARES SET ISBN = '%s',  FORMA_ADQUISICION = '%s', FECHA_ADQUISICION = '%s', OBSERVACIONES = '%s', FECHA_BAJA = '%s', MOTIVO_BAJA = '%s', UBICACION = '%s'",
                ejemplar.getIsbn(), ejemplar.getFormaAdquisicion(), ejemplar.getFechaAdquisicion(),
                ejemplar.getObservaciones(), ejemplar.getFechaBaja(), ejemplar.getMotivoBaja(),
                ejemplar.getUbicacion());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(Ejemplar t) {
        return null;
    }

    private Ejemplar toEjemplar(ResultSet resultados) {
        Ejemplar ejemplar = new Ejemplar();
        try {
            ejemplar.setIsbn(resultados.getString("isbn"));
            ejemplar.setTitulo(resultados.getString("titulo"));
            ejemplar.setSubtitulo(resultados.getString("subtitulo"));
            ejemplar.setPrimerAutor(resultados.getString("primer_autor"));
            ejemplar.setSegundoAutor(resultados.getString("segundo_autor"));
            ejemplar.setTercerAutor(resultados.getString("tercer_autor"));
            ejemplar.setGenero(resultados.getString("genero"));
            ejemplar.setFormaAdquisicion(resultados.getString("forma_adquisicion"));
            ejemplar.setFechaAdquisicion(resultados.getString("fecha_adquisicion"));
            ejemplar.setObservaciones(resultados.getString("observaciones"));
            ejemplar.setFechaBaja(resultados.getString("fecha_baja"));
            ejemplar.setMotivoBaja(resultados.getString("motivo_baja"));
            ejemplar.setTipo(new TipoObra(resultados.getLong("tipo_id"), resultados.getString("tipo_nombre")));
            ejemplar.setArea(new AreaTematica(resultados.getLong("area_id"), resultados.getString("area_nombre")));
            ejemplar.setCodigoIdentificatorio(new CodigoIdentificatorio(resultados.getString("codigo"),
                    resultados.getString("pasillo"), resultados.getString("estanteria"),
                    resultados.getString("estante")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ejemplar;
    }

    @Override
    public List<Ejemplar> findAllByObraIsbn(String isbn) {
        String sql = String.format("SELECT * FROM EJEMPLARES WHERE ISBN = '%s'", isbn);
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            List<Ejemplar> ejemplares = new LinkedList<>();
            while (resultados.next()) {
                ejemplares.add(toEjemplar(resultados));
            }
            return ejemplares;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}

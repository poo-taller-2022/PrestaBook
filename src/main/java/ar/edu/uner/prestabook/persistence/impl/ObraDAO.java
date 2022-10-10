package ar.edu.uner.prestabook.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.uner.prestabook.connection.ConnectionProvider;
import ar.edu.uner.prestabook.model.AreaTematica;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.model.TipoObra;
import ar.edu.uner.prestabook.persistence.IObraDAO;

public class ObraDAO implements IObraDAO {

    /**
     * Singleton instance of the class
     */
    private static final ObraDAO instance = new ObraDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private ObraDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static ObraDAO getInstance() {
        return instance;
    }

    @Override
    public List<Obra> findAll() {
        String sql = "SELECT *,"
                + "TIPOS_OBRA.id AS 'tipo_id',"
                + "TIPOS_OBRA.nombre AS 'tipo_nombre',"
                + "areas_tematicas.id AS 'area_id',"
                + "areas_tematicas.nombre AS 'area_nombre' "
                + "FROM OBRAS "
                + "INNER JOIN tipos_obra ON OBRAS.tipo_obra = tipos_obra.id "
                + "INNER JOIN areas_tematicas ON OBRAS.area_tematica = areas_tematicas.id";
        List<Obra> obras = new LinkedList<>();
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            while (resultados.next()) {
                obras.add(toObra(resultados));
            }
            return obras;
        } catch (SQLException e) {
            e.printStackTrace();
            return obras;
        }
    }

    @Override
    public Obra findById(Object isbn) {
        String sql = String.format("SELECT * FROM OBRAS WHERE ISBN = %s", isbn.toString());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            Obra obra = null;
            if (resultados.next()) {
                obra = toObra(resultados);
            }
            return obra;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer insert(Obra obra) {
        String sql = String.format(
                "INSERT INTO OBRAS (ISBN, TITULO, SUBTITULO, PRIMER_AUTOR, SEGUNDO_AUTOR, TERCER_AUTOR, GENERO, TIPO_OBRA, AREA_TEMATICA) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s', '%s')",
                obra.getIsbn(), obra.getTitulo(), obra.getSubtitulo(), obra.getPrimerAutor(), obra.getSegundoAutor(),
                obra.getTercerAutor(), obra.getGenero(), obra.getArea().getId(), obra.getTipo().getId());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer update(Obra obra) {
        String sql = String.format(
                "UPDATE OBRAS SET ISBN = '%s', TITULO = '%s', SUBTITULO = '%s', PRIMER_AUTOR = '%s', SEGUNDO_AUTOR = '%s', TERCER_AUTOR = '%s', GENERO = '%s'",
                obra.getIsbn(), obra.getTitulo(), obra.getSubtitulo(), obra.getPrimerAutor(), obra.getSegundoAutor(),
                obra.getTercerAutor(), obra.getGenero());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(Obra t) {
        return null;
    }

    /**
     * Converts a ResultSet into an Obra
     * 
     * @param resultados the ResultSet with the rows obtained from the database query
     * @return an Obra
     */
    private Obra toObra(ResultSet resultados) {
        Obra obra = new Obra();
        try {
            obra.setIsbn(resultados.getString("isbn"));
            obra.setTitulo(resultados.getString("titulo"));
            obra.setSubtitulo(resultados.getString("subtitulo"));
            obra.setPrimerAutor(resultados.getString("primer_autor"));
            obra.setSegundoAutor(resultados.getString("segundo_autor"));
            obra.setTercerAutor(resultados.getString("tercer_autor"));
            obra.setGenero(resultados.getString("genero"));
            obra.setTipo(new TipoObra(resultados.getLong("tipo_id"), resultados.getString("tipo_nombre")));
            obra.setArea(new AreaTematica(resultados.getLong("area_id"), resultados.getString("area_nombre")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obra;
    }

}

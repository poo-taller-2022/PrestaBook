package ar.edu.uner.prestabook.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.connection.ConnectionProvider;
import ar.edu.uner.prestabook.model.Edicion;
import ar.edu.uner.prestabook.model.Formato;
import ar.edu.uner.prestabook.persistence.IEdicionDAO;
import ar.edu.uner.prestabook.persistence.IFormatoDAO;

public class EdicionDAO implements IEdicionDAO {

    /**
     * Singleton instance of the class
     */
    private static final EdicionDAO instance = new EdicionDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private EdicionDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static EdicionDAO getInstance() {
        return instance;
    }

    @Override
    public List<Edicion> findAll() {
        String sql = "SELECT * FROM EDICIONES";
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            List<Edicion> ediciones = new LinkedList<>();
            while (resultados.next()) {
                ediciones.add(toEdicion(resultados));
            }
            return ediciones;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Edicion findById(Object id) {
        String sql = String.format("SELECT * FROM EDICIONES WHERE ID = %s", id.toString());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            Edicion edicion = null;
            if (resultados.next()) {
                edicion = toEdicion(resultados);
            }
            return edicion;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer insert(Edicion edicion) {
        String sql = String.format(
                "INSERT INTO EDICIONES (EDITORIAL, PAIS, NUMERO, ANIO, VOLUMENES, PAGINAS, IDIOMA) "
                        + "VALUES ('%s','%s','%d','%d','%d','%d','%s')",
                edicion.getEditorial(), edicion.getPais(), edicion.getNumero(), edicion.getAnio(),
                edicion.getVolumenes(), edicion.getPaginas(), edicion.getIdioma());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer update(Edicion edicion) {
        String sql = String.format(
                "UPDATE EDICIONES SET EDITORIAL = '%s', PAIS = '%s', NUMERO = '%d', ANIO = '%d', VOLUMENES = '%d', "
                        + "PAGINAS = '%d', IDIOMA = '%s', FORMATO = '%s' WHERE ID = '%s'",
                edicion.getId(), edicion.getEditorial(), edicion.getPais(), edicion.getNumero(), edicion.getAnio(),
                edicion.getVolumenes(), edicion.getPaginas(), edicion.getIdioma());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(Edicion edicion) {
        return 0;
    }

    /**
     * Converts a ResultSet into an Edicion
     * 
     * @param resultados the ResultSet with the rows obtained from the database
     *                   query
     * @return an Edicion
     */
    private Edicion toEdicion(ResultSet resultados) {
        try {
            IFormatoDAO formatoDAO = DaoFactory.getFormatoDAO();
            // TODO: crear metodo en formatoDAO para que traiga todos los formatos de una
            // edicion
            List<Formato> formato = (List<Formato>) formatoDAO.findById(resultados.getInt(9));

            return new Edicion(resultados.getLong(1), resultados.getString(2), resultados.getString(3),
                    resultados.getInt(4), resultados.getInt(5), resultados.getLong(6), resultados.getInt(7),
                    resultados.getString(8), formato);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

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
import ar.edu.uner.prestabook.persistence.IAreaTematicaDAO;

public class AreaTematicaDAO implements IAreaTematicaDAO {

    /**
     * Singleton instance of the class
     */
    private static final AreaTematicaDAO instance = new AreaTematicaDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private AreaTematicaDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static AreaTematicaDAO getInstance() {
        return instance;
    }

    @Override
    public List<AreaTematica> findAll() {
        String sql = "SELECT * FROM AREAS_TEMATICAS";
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            List<AreaTematica> areasTematicas = new LinkedList<>();
            while (resultados.next()) {
                areasTematicas.add(toAreaTematica(resultados));
            }
            return areasTematicas;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public AreaTematica findById(Object id) {
        String sql = String.format("SELECT * FROM AREAS_TEMATICAS WHERE ID = %s", id.toString());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            AreaTematica areaTematica = null;
            if (resultados.next()) {
                areaTematica = toAreaTematica(resultados);
            }
            return areaTematica;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer insert(AreaTematica areaTematica) {
        String sql = String.format("INSERT INTO AREAS_TEMATICAS (NOMBRE) VALUES ('%s')", areaTematica.getNombre());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer update(AreaTematica areaTematica) {
        String sql = String.format("UPDATE AREAS_TEMATICAS SET NOMBRE = '%s' WHERE ID = '%s'", areaTematica.getNombre(),
                areaTematica.getId());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(AreaTematica areaTematica) {
        return 0;
    }

    /**
     * Converts a ResultSet into an AreaTematica
     * 
     * @param resultados the ResultSet with the rows obtained from the database
     *                   query
     * @return an AreaTematica
     */
    private AreaTematica toAreaTematica(ResultSet resultados) {
        AreaTematica area = new AreaTematica();
        try {
            area.setId(resultados.getLong(1));
            area.setNombre(resultados.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return area;
    }
}
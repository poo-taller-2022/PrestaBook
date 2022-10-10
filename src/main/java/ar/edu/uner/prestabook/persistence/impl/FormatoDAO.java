package ar.edu.uner.prestabook.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ar.edu.uner.prestabook.connection.ConnectionProvider;
import ar.edu.uner.prestabook.model.Formato;
import ar.edu.uner.prestabook.persistence.IFormatoDAO;

public class FormatoDAO implements IFormatoDAO {

    /**
     * Singleton instance of the class
     */
    private static final FormatoDAO instance = new FormatoDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private FormatoDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static FormatoDAO getInstance() {
        return instance;
    }

    @Override
    public List<Formato> findAll() {
        String sql = "SELECT * FROM FORMATO";
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            List<Formato> formato = new LinkedList<>();
            while (resultados.next()) {
                formato.add(toFormato(resultados));
            }
            return formato;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Formato findById(Object id) {
        String sql = String.format("SELECT * FROM FORMATO WHERE ID = %s", id.toString());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            Formato formato = null;
            if (resultados.next()) {
                formato = toFormato(resultados);
            }
            return formato;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer insert(Formato formato) {
        String sql = String.format("INSERT INTO FORMATO (NOMBRE) VALUES ('%s')", formato.getNombre());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer update(Formato formato) {
        String sql = String.format("UPDATE FORMATO SET NOMBRE = '%s' WHERE ID = '%s'", formato.getNombre(),
                formato.getId());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(Formato formato) {
        return 0;
    }

    /**
     * Converts a ResultSet into a Formato
     * 
     * @param resultados the ResultSet with the rows obtained from the database
     *                   query
     * @return a Formato
     */
    private Formato toFormato(ResultSet resultados) {
        Formato formato = new Formato();
        try {
            formato.setId(resultados.getLong(1));
            formato.setNombre(resultados.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formato;
    }
}

package ar.edu.uner.prestabook.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ar.edu.uner.prestabook.connection.ConnectionProvider;
import ar.edu.uner.prestabook.model.Coleccion;
import ar.edu.uner.prestabook.persistence.IColeccionDAO;

public class ColeccionDAO implements IColeccionDAO {
    
    /**
     * Singleton instance of the class
     */
    private static final ColeccionDAO instance = new ColeccionDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private ColeccionDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static ColeccionDAO getInstance() {
        return instance;
    }

    @Override
    public List<Coleccion> findAll() {
        String sql = "SELECT * FROM colecciones";
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            List<Coleccion> colecciones = new LinkedList<>();
            while (resultados.next()) {
                colecciones.add(toColeccion(resultados));
            }
            return colecciones;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Coleccion findById(Object id) {
        return null;
    }

    @Override
    public Integer insert(Coleccion t) {
        return null;
    }

    @Override
    public Integer update(Coleccion t) {
        return null;
    }

    @Override
    public Integer delete(Coleccion t) {
        return null;
    }

    private Coleccion toColeccion(ResultSet resultados) {
        Coleccion coleccion = new Coleccion();
        try {
            coleccion.setIsbn(resultados.getString("isbn"));
            coleccion.setTitulo(resultados.getString("titulo"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coleccion;
    }
}

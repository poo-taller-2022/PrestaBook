package ar.edu.uner.prestabook.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.uner.prestabook.connection.ConnectionProvider;
import ar.edu.uner.prestabook.persistence.IUsuarioDAO;
import ar.edu.uner.prestabook.security.PasswordEncrypter;

public class UsuarioDAO implements IUsuarioDAO {

    /**
     * Singleton instance of the class
     */
    private static final UsuarioDAO instance = new UsuarioDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private UsuarioDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static UsuarioDAO getInstance() {
        return instance;
    }

    
    /**
     * allows the user access to application
     * @param tipo Refers to the differents type user
     * @param correo Email of user
     * @param contrasenia of user
     * @return String Confirming that the user is registered
     */
    @Override
    public String buscarUsuarioRegistrado(String tipo, String correo, String contrasenia) {
        String sql = String.format("SELECT EMAIL, CONTRASENIA FROM %s WHERE EMAIL ='%s'",
                tipo, correo);
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            if (resultados.next() && Boolean.TRUE
                    .equals(PasswordEncrypter.verify(resultados.getString("contrasenia"), contrasenia))) {
                return "usuario encontrado";
            } else {
                return "usuario no encontrado";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * get name of user (used in GUI)
     * @param tipo Refers to the differents type user
     * @param correo Email of user
     * @return String with name and surname
     */
    @Override
    public String buscarNombre(String tipo, String correo) {
        String sql = String.format("SELECT NOMBRE, APELLIDO FROM %s WHERE EMAIL ='%s'", tipo, correo);
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);) {
            ResultSet resultados = statement.executeQuery();

            if (resultados.next()) {
                String nombre = resultados.getString("nombre");
                String apellido = resultados.getString("apellido");
                return (nombre + " " + apellido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

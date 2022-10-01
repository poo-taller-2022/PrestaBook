package ar.edu.uner.prestabook.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ar.edu.uner.prestabook.connection.ConnectionProvider;
import ar.edu.uner.prestabook.jframe.Login;
import ar.edu.uner.prestabook.model.AreaTematica;

public class UsuariosDAO {
	
	Connection conn;
	
	public UsuariosDAO(Connection conn) {
		this.conn = conn;
	}

	public Integer guardar(String nombre, String apellido, String correo, String contrasenia) {
		String sql = String.format("INSERT INTO USUARIOS (NOMBRE,APELLIDO ,CORREO,CONTRASENIA) VALUES (?,?,?,?)");
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1,nombre);
			statement.setString(2,apellido);
			statement.setString(3,correo);
			statement.setString(4,contrasenia);
			
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String buscarUsuarioRegistrado(String correo, String contrasenia) {
		String sql = String.format("SELECT CORREO,CONTRASENIA FROM USUARIOS WHERE CORREO ='" + correo + "' AND CONTRASENIA = '" + contrasenia + "'");
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			if (resultados.next()) {
				String busqueda_usuario = "usuario encontrado";
				return busqueda_usuario;
			} else {
				String busqueda_usuario = "usuario no encontrado";
				return busqueda_usuario;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String buscarNombre(String correo) {
		String sql = String.format("SELECT NOMBRE,APELLIDO FROM USUARIOS WHERE CORREO ='" + correo + "'");
		try (PreparedStatement statement = conn.prepareStatement(sql);) {
			ResultSet resultados = statement.executeQuery();
			
			if (resultados.next()) {
				String nombre = resultados.getString("nombre");
				String apellido = resultados.getString("apellido");
				String busqueda_nombre = (nombre + " " + apellido);
				return busqueda_nombre;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

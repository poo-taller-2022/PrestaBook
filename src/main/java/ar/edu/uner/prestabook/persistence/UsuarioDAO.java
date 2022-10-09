package ar.edu.uner.prestabook.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

	Connection conn;

	public UsuarioDAO(Connection conn) {
		this.conn = conn;
	}

	public String buscarUsuarioRegistrado(String tipo, String correo, String contrasenia) {
		String sql = String.format("SELECT EMAIL,CONTRASENIA FROM " + tipo.toUpperCase() + " WHERE EMAIL ='" + correo
				+ "' AND CONTRASENIA = '" + contrasenia + "'");
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			if (resultados.next()) {
				return "usuario encontrado";
			} else {
				return "usuario no encontrado";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String buscarNombre(String tipo, String correo) {
		String sql = String
				.format("SELECT NOMBRE,APELLIDO FROM " + tipo.toUpperCase() + " WHERE EMAIL ='" + correo + "'");
		try (PreparedStatement statement = conn.prepareStatement(sql);) {
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

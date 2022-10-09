package ar.edu.uner.prestabook.app;

import java.sql.Connection;
import java.sql.SQLException;

import ar.edu.uner.prestabook.connection.ConnectionProvider;
import ar.edu.uner.prestabook.jframe.IniciarSesion;

public class App {

	public static void main(String[] args) {
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			IniciarSesion.main(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

package ar.edu.uner.prestabook.app;

import java.sql.Connection;
import java.sql.SQLException;

import ar.edu.uner.prestabook.connection.ConnectionProvider;
import ar.edu.uner.prestabook.jframe.Login;

public class App {

	public static void main(String[] args) {
		try {
			Connection conn = ConnectionProvider.getConnection();
			Login.main(conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

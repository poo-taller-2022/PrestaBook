package ar.edu.uner.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Connect {

	static Logger logger = Logger.getLogger(Connect.class.getCanonicalName());

	/**
	 * Connect to a sample database
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			String url = "jdbc:sqlite:src/main/resources/prestabook.db";
			conn = DriverManager.getConnection(url);

			logger.info("Connection to SQLite has been established.");
			return conn;
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		return conn;
	}

}
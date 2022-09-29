package ar.edu.uner.prestabook.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import ar.edu.uner.prestabook.common.Configs;

/**
 * Database connection class
 *
 */
public class ConnectionProvider {

	/**
	 * A connection (session) with a specific database
	 */
	private static Connection connection;

	/**
	 * Private constructor to avoid instantiation
	 */
	private ConnectionProvider() {
	}

	/**
	 * Tries to establish a connection to the database URL defined in the .env
	 * properties file
	 * 
	 * @return
	 * @throws SQLException if a database access error occurs or the URL is null
	 */
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		if (connection == null) {
			connection = DriverManager.getConnection(Configs.getDatasourceUrl());
		}
		return connection;
	}

}

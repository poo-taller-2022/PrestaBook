package ar.edu.uner.prestabook.common;

import java.io.IOException;
import java.util.Properties;

import lombok.Getter;

/**
 * Configuration class created to read properties from an .env file
 *
 */
public class Configs {

	/**
	 * Private constructor to avoid instantiation
	 */
	private Configs() {
	}

	/**
	 * URL of the database file
	 */
	@Getter
	private static String datasourceUrl;

	static {
		Properties properties = new Properties();
		try {
			properties.load(Configs.class.getResourceAsStream("/properties.env"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		datasourceUrl = properties.getProperty("datasource");
	}
}

package ar.edu.uner.prestabook.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import ar.edu.uner.prestabook.connection.ConnectionProvider;
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

    public static void loadConfigs() {
        try (Connection conn = ConnectionProvider.getConnection();
                Statement statement = conn.createStatement()) {
            statement.executeUpdate(
                    "INSERT OR IGNORE INTO CONFIGS(key_col, value_col) VALUES('default_loan_time', '4')");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}

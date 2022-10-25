package ar.edu.uner.prestabook.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.jdbc.ScriptRunner;

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

    /**
     * Loads demo data into the database from an sql script
     */
    public static void loadDemoData() {
        try (Connection conn = ConnectionProvider.getConnection();
                Statement statement = conn.createStatement();
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream("src/main/resources/script.sql"));) {
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setLogWriter(null);
            runner.runScript(reader);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

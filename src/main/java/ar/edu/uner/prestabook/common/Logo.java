package ar.edu.uner.prestabook.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Class that prints the logo of the application
 *
 */
public class Logo {

    /**
     * Private constructor to avoid instantiation
     */
    private Logo() {
    }

    /**
     * Prints the logo along with its version
     */
    public static void print() {
        try {
            Properties properties = new Properties();
            properties.load(Logo.class.getResourceAsStream("/maven.properties"));
            String name = properties.getProperty("project.name");
            String version = properties.getProperty("project.version");
            try (BufferedReader br = new BufferedReader(new FileReader(new File("src/main/resources/logo.txt")))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.println(String.format("Running %s v%s", name, version));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load logo");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Connect {
	
	static Logger logger = Logger.getLogger("Prestabook");
	
	/**
	 * Connect to a sample database
	 */
   public static void connect() {
       Connection conn = null;
       try {
           String url = "jdbc:sqlite:src/main/resources/prestabook.db";
           conn = DriverManager.getConnection(url);
           
          	logger.info("Connection to SQLite has been established.");
           
       } catch (SQLException e) {
    	   logger.severe(e.getMessage());
       } finally {
           try {
               if (conn != null) {
                   conn.close();
               }
           } catch (SQLException ex) {
        	   logger.severe(ex.getMessage());
           }
       }
   }
   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
       connect();
   }
}
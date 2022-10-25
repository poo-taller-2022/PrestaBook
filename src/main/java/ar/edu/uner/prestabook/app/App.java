package ar.edu.uner.prestabook.app;

import ar.edu.uner.prestabook.common.Configs;
import ar.edu.uner.prestabook.common.Logo;
import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.jframe.IniciarSesion;


/**
* Main class that provides the session to run the app.
*
*/

public class App {

    public static void main(String[] args) {
        HibernateConnection.openCurrentSession();
        Configs.loadDemoData();
        Logo.print();
        IniciarSesion.main();
    }
}

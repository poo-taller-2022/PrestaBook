package ar.edu.uner.prestabook.app;

import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.jframe.IniciarSesion;

public class App {

    public static void main(String[] args) {
        HibernateConnection.openCurrentSession();
        IniciarSesion.main();
    }

}

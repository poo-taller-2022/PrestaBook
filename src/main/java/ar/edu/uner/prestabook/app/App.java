package ar.edu.uner.prestabook.app;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.common.Logo;
import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.jframe.IniciarSesion;
import ar.edu.uner.prestabook.model.AreaTematica;
import ar.edu.uner.prestabook.model.CodigoIdentificatorio;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.model.TipoObra;
import ar.edu.uner.prestabook.persistence.ICodigoIdentificatorioDAO;
import ar.edu.uner.prestabook.persistence.IEjemplarDAO;

public class App {

    public static void main(String[] args) {
        HibernateConnection.openCurrentSession();
        Logo.print();
        IniciarSesion.main();
    }
}

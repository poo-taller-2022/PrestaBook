package ar.edu.uner.prestabook.connection;

import java.sql.SQLException;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.model.AreaTematica;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.model.TipoObra;

public class App {

	public static void main(String[] args) throws SQLException {
		//Connection conn = ConnectionProvider.getConnection();
//		Obra obra = new Obra();
//		AreaTematica area = new AreaTematica();
//		area.setId((long) 1);
//		TipoObra tipo = new TipoObra();
//		tipo.setId((long) 1);
//		obra.setIsbn("12345");
//		obra.setTitulo("Obra mágica");
//		obra.setArea(area);
//		obra.setTipo(tipo);
//		DaoFactory.getObraDAO().insert(obra);
		System.out.println(DaoFactory.getObraDAO().findAll());
	}
}

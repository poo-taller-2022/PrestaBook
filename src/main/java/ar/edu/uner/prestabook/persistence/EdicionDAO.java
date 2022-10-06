package ar.edu.uner.prestabook.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ar.edu.uner.prestabook.model.Edicion;
import ar.edu.uner.prestabook.model.Formato;

public class EdicionDAO implements IEdicionDAO {

	Connection conn;
	
	public EdicionDAO(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public List<Edicion> findAll() {
		String sql = "SELECT * FROM EDICIONES";
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			List<Edicion> ediciones = new LinkedList<>();
			while (resultados.next()) {
				ediciones.add(toEdicion(resultados));
			}
			return ediciones;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public Edicion findById(Object id) {
		String sql = String.format("SELECT * FROM EDICIONES WHERE ID = %s", id.toString());
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			Edicion edicion = null;
			if (resultados.next()) {
				edicion = toEdicion(resultados);
			}
			return edicion;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Integer insert(Edicion edicion) {	
		String sql = String.format("INSERT INTO EDICIONES (EDITORIAL, PAIS, NUMERO, ANIO, VOLUMENES, PAGINAS, IDIOMA) VALUES (?,?,?,?,?,?,?)");
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, edicion.getEditorial());
			statement.setString(2, edicion.getPais());
			statement.setInt(3, edicion.getNumero());
			statement.setInt(4, edicion.getAnio());
			statement.setLong(5, edicion.getVolumenes());
			statement.setInt(6, edicion.getPaginas());
			statement.setString(7, edicion.getIdioma());
			
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer update(Edicion edicion) {
		String sql = String.format("UPDATE EDICIONES SET ID, EDITORIAL, PAIS, NUMERO, ANIO, VOLUMENES, PAGINAS, IDIOMA, FORMATO = '%s' WHERE ID = '%s'", edicion.getId(), edicion.getEditorial(), edicion.getPais(), edicion.getNumero(), edicion.getAnio(), edicion.getVolumenes(), edicion.getPaginas(), edicion.getIdioma());
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer delete(Edicion edicion) {
		return 0;
	}

	/**
	 * Receives a ResultSet obtained from a query and transforms it to an entity
	 * 
	 * @param resultados The result set from the db
	 */
	
	private Edicion toEdicion(ResultSet resultados) {
		try {
			FormatoDAO formatoDAO = new FormatoDAO(this.conn);
			List<Formato> formato = (List<Formato>) formatoDAO.findById(resultados.getInt(9));
			
			return new Edicion(resultados.getLong(1), resultados.getString(2), resultados.getString(3), resultados.getInt(4), resultados.getInt(5), resultados.getLong(6), resultados.getInt(7), resultados.getString(8),formato);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}

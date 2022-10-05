package ar.edu.uner.prestabook.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ar.edu.uner.prestabook.connection.ConnectionProvider;
import ar.edu.uner.prestabook.model.TipoObra;

public class TipoObraDAO implements ITipoObraDAO {

	@Override
	public List<TipoObra> findAll() {
		String sql = "SELECT * FROM TIPOS_OBRA";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			List<TipoObra> tiposObra = new LinkedList<>();
			while (resultados.next()) {
				tiposObra.add(toTipoObra(resultados));
			}
			return tiposObra;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public TipoObra findById(Object id) {
		String sql = String.format("SELECT * FROM TIPOS_OBRA WHERE ID = %s", id.toString());
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			TipoObra tiposObras = null;
			if (resultados.next()) {
				tiposObras = toTipoObra(resultados);
			}
			return tiposObras;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	//@Override
	public  Integer insert(TipoObra tipoObra) {
		String sql = String.format("INSERT INTO TIPOS_OBRA (NOMBRE) VALUES ('%s')", tipoObra.getNombre());
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer update(TipoObra tipoObra) {
		String sql = String.format("UPDATE TIPOS_OBRA SET NOMBRE = '%s' WHERE ID = '%s'", tipoObra.getNombre(),
				tipoObra.getId());
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer delete(TipoObra t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private TipoObra toTipoObra(ResultSet resultados) {
		TipoObra area = new TipoObra();
		try {
			area.setId(resultados.getLong(1));
			area.setNombre(resultados.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return area;
	}
	
	

}
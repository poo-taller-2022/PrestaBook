package ar.edu.uner.prestabook.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ar.edu.uner.prestabook.connection.ConnectionProvider;
import ar.edu.uner.prestabook.model.AreaTematica;

public class AreaTematicaDAO implements IAreaTematicaDAO {

	@Override
	public List<AreaTematica> findAll() {
		String sql = "SELECT * FROM AREAS_TEMATICAS";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			List<AreaTematica> areasTematicas = new LinkedList<>();
			while (resultados.next()) {
				areasTematicas.add(toAreaTematica(resultados));
			}
			return areasTematicas;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public AreaTematica findById(Object id) {
		String sql = String.format("SELECT * FROM AREAS_TEMATICAS WHERE ID = %s", id.toString());
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			AreaTematica areaTematica = null;
			if (resultados.next()) {
				areaTematica = toAreaTematica(resultados);
			}
			return areaTematica;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer insert(AreaTematica areaTematica) {
		String sql = String.format("INSERT INTO AREAS_TEMATICAS (NOMBRE) VALUES ('%s')", areaTematica.getNombre());
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer update(AreaTematica areaTematica) {
		String sql = String.format("UPDATE AREAS_TEMATICAS SET NOMBRE = '%s' WHERE ID = '%s'", areaTematica.getNombre(),
				areaTematica.getId());
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer delete(AreaTematica areaTematica) {
		return 0;
	}

	/**
	 * Receives a ResultSet obtained from a query and transforms it to an entity
	 * 
	 * @param resultados The result set from the db
	 * @return an Area Tematica entity
	 */
	private AreaTematica toAreaTematica(ResultSet resultados) {
		AreaTematica area = new AreaTematica();
		try {
			area.setId(resultados.getLong(1));
			area.setNombre(resultados.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return area;
	}
}

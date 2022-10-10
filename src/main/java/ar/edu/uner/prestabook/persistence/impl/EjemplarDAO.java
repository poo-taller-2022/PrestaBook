package ar.edu.uner.prestabook.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ar.edu.uner.prestabook.connection.ConnectionProvider;
import ar.edu.uner.prestabook.model.Ejemplar;
import ar.edu.uner.prestabook.persistence.IEjemplarDAO;

public class EjemplarDAO implements IEjemplarDAO {

	/**
	 * Singleton instance of the class
	 */
	private static final EjemplarDAO instance = new EjemplarDAO();

	/**
	 * Private constructor to avoid instantiation
	 */
	private EjemplarDAO() {
	}

	/**
	 * 
	 * @return the singleton instance of the class
	 */
	public static EjemplarDAO getInstance() {
		return instance;
	}

	@Override
	public List<Ejemplar> findAll() {
		String sql = "SELECT * FROM EJEMPLARES";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			List<Ejemplar> ejemplares = new LinkedList<>();
			while (resultados.next()) {
				ejemplares.add(toEjemplar(resultados));
			}
			return ejemplares;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public Ejemplar findById(Object isbn) {
		String sql = String.format("SELECT * FROM EJEMPLARES WHERE ISBN = %s", isbn.toString());
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			Ejemplar ejemplar = null;
			if (resultados.next()) {
				ejemplar = toEjemplar(resultados);
			}
			return ejemplar;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer insert(Ejemplar ejemplar) {
		String sql = String.format(
				"INSERT INTO EJEMPLARES (ISBN, TITULO, SUBTITULO, PRIMER_AUTOR, SEGUNDO_AUTOR, TERCER_AUTOR, GENERO, FORMA_ADQUISICION, FECHA_ADQUISICION, OBSERVACIONES, FECHA_BAJA, MOTIVO_BAJA, UBICACION) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s', '%s')",
				ejemplar.getIsbn(), ejemplar.getTitulo(), ejemplar.getSubtitulo(), ejemplar.getPrimerAutor(),
				ejemplar.getSegundoAutor(), ejemplar.getTercerAutor(), ejemplar.getGenero(),
				ejemplar.getFormaAdquisicion(), ejemplar.getFechaAdquisicion(), ejemplar.getObservaciones(),
				ejemplar.getFechaBaja(), ejemplar.getMotivoBaja(), ejemplar.getUbicacion());
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer update(Ejemplar ejemplar) {
		String sql = String.format(
				"UPDATE EJEMPLARES SET ISBN = '%s', TITULO = '%s', SUBTITULO = '%s', PRIMER_AUTOR = '%s', SEGUNDO_AUTOR = '%s', TERCER_AUTOR = '%s', GENERO = '%s', FORMA_ADQUISICION = '%s', FECHA_ADQUISICION = '%s', OBSERVACIONES = '%s', FECHA_BAJA = '%s', MOTIVO_BAJA = '%s', UBICACION = '%s'",
				ejemplar.getIsbn(), ejemplar.getTitulo(), ejemplar.getSubtitulo(), ejemplar.getPrimerAutor(),
				ejemplar.getSegundoAutor(), ejemplar.getTercerAutor(), ejemplar.getGenero(),
				ejemplar.getFormaAdquisicion(), ejemplar.getFechaAdquisicion(), ejemplar.getObservaciones(),
				ejemplar.getFechaBaja(), ejemplar.getMotivoBaja(), ejemplar.getUbicacion());
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer delete(Ejemplar t) {
		return null;
	}

	private Ejemplar toEjemplar(ResultSet resultados) {
		Ejemplar ejemplar = new Ejemplar();
		try {
			ejemplar.setArea(null);
			ejemplar.setTipo(null);
			ejemplar.setIsbn(resultados.getString(1));
			ejemplar.setTitulo(resultados.getString(2));
			ejemplar.setSubtitulo(resultados.getString(3));
			ejemplar.setPrimerAutor(resultados.getString(4));
			ejemplar.setSegundoAutor(resultados.getString(5));
			ejemplar.setTercerAutor(resultados.getString(6));
			ejemplar.setGenero(resultados.getString(7));
			ejemplar.setFormaAdquisicion(resultados.getString(8));
			ejemplar.setFechaAdquisicion(resultados.getString(9));
			ejemplar.setObservaciones(resultados.getString(10));
			ejemplar.setFechaBaja(resultados.getString(11));
			ejemplar.setMotivoBaja(resultados.getString(12));
			ejemplar.setUbicacion(resultados.getString(13));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ejemplar;
	}

	@Override
	public List<Ejemplar> findAllByObraIsbn(String isbn) {
		String sql = String.format("SELECT * FROM EJEMPLARES WHERE ISBN = '%s'", isbn);
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			List<Ejemplar> ejemplares = new LinkedList<>();
			while (resultados.next()) {
				ejemplares.add(toEjemplar(resultados));
			}
			return ejemplares;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

}

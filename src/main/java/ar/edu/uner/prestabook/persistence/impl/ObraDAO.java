package ar.edu.uner.prestabook.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.connection.ConnectionProvider;
import ar.edu.uner.prestabook.model.Obra;
import ar.edu.uner.prestabook.persistence.IObraDAO;

public class ObraDAO implements IObraDAO {

	/**
	 * Singleton instance of the class
	 */
	private static final ObraDAO instance = new ObraDAO();

	/**
	 * Private constructor to avoid instantiation
	 */
	private ObraDAO() {
	}

	/**
	 * 
	 * @return the singleton instance of the class
	 */
	public static ObraDAO getInstance() {
		return instance;
	}

	@Override
	public List<Obra> findAll() {
		String sql = "SELECT * FROM OBRAS";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			List<Obra> obras = new LinkedList<>();
			while (resultados.next()) {
				obras.add(toObra(resultados));
			}
			return obras;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public Obra findById(Object isbn) {
		String sql = String.format("SELECT * FROM OBRAS WHERE ISBN = %s", isbn.toString());
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			Obra obra = null;
			if (resultados.next()) {
				obra = toObra(resultados);
			}
			return obra;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer insert(Obra obra) {
		String sql = String.format(
				"INSERT INTO OBRAS (ISBN, TITULO, SUBTITULO, PRIMER_AUTOR, SEGUNDO_AUTOR, TERCER_AUTOR, GENERO, TIPO_OBRA, AREA_TEMATICA) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s', '%s')",
				obra.getIsbn(), obra.getTitulo(), obra.getSubtitulo(), obra.getPrimerAutor(), obra.getSegundoAutor(),
				obra.getTercerAutor(), obra.getGenero(), obra.getArea().getId(), obra.getTipo().getId());
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer update(Obra obra) {
		String sql = String.format(
				"UPDATE OBRAS SET ISBN = '%s', TITULO = '%s', SUBTITULO = '%s', PRIMER_AUTOR = '%s', SEGUNDO_AUTOR = '%s', TERCER_AUTOR = '%s', GENERO = '%s'",
				obra.getIsbn(), obra.getTitulo(), obra.getSubtitulo(), obra.getPrimerAutor(), obra.getSegundoAutor(),
				obra.getTercerAutor(), obra.getGenero());
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer delete(Obra t) {
		return null;
	}

	/**
	 * Convierte un ResultSet a una Obra
	 * 
	 * @param resultados el ResultSet con las filas de la query hecha a la base de
	 *                   datos
	 * @return Una entidad de Obra
	 */
	private Obra toObra(ResultSet resultados) {
		Obra obra = new Obra();
		try {
			obra.setIsbn(resultados.getString(1));
			obra.setTitulo(resultados.getString(2));
			obra.setSubtitulo(resultados.getString(3));
			obra.setPrimerAutor(resultados.getString(4));
			obra.setSegundoAutor(resultados.getString(5));
			obra.setTercerAutor(resultados.getString(6));
			obra.setGenero(resultados.getString(7));
			obra.setEjemplares(DaoFactory.getEjemplarDAO().findAllByObraIsbn(obra.getIsbn()));
			obra.setArea(DaoFactory.getAreaTematicaDAO().findById(resultados.getInt(8)));
			obra.setTipo(DaoFactory.getTipoObraDAO().findById(resultados.getInt(9)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obra;
	}

}

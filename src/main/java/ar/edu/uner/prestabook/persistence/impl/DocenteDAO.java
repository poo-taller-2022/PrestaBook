package ar.edu.uner.prestabook.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ar.edu.uner.prestabook.model.Docente;
import ar.edu.uner.prestabook.persistence.IDocenteDAO;

public class DocenteDAO implements IDocenteDAO {

	/**
	 * Singleton instance of the class
	 */
	private static final DocenteDAO instance = new DocenteDAO();

	/**
	 * Private constructor to avoid instantiation
	 */
	private DocenteDAO() {
	}

	/**
	 * 
	 * @return the singleton instance of the class
	 */
	public static DocenteDAO getInstance() {
		return instance;
	}
		Connection conn;

	public DocenteDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Docente> findAll() {
		String sql = "SELECT * FROM DOCENTES";
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			List<Docente> docentes = new LinkedList<>();
			while (resultados.next()) {
				docentes.add(toDocente(resultados));
			}
			return docentes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public Docente findById(Object id) {

		String sql = String.format("SELECT * FROM DOCENTES WHERE ID = %s", id.toString());
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			Docente docente = null;
			if (resultados.next()) {
				docente = toDocente(resultados);
			}
			return docente;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer insert(Docente docente) {
		String sql = String.format(
				"INSERT INTO DOCENTES (NOMBRE, APELLIDO, TIPO_DOCUMENTO, DNI,"
						+ "EMAIL, CELULAR, FECHA_NACIMIENTO, SEXO, NACIONALIDAD, DOMICILIO,"
						+ "CODIGO_POSTAL, DEPARTAMENTO, LOCALIDAD, CONTRASENIA) VALUES ('%s','%s','%s','%s','%s','%s',"
						+ "'%s','%s','%s','%s','%s','%s','%s','%s')",
				docente.getNombre(), docente.getApellido(), docente.getTipoDocumento(), docente.getDocumento(),
				docente.getEmail(), docente.getCelular(), docente.getFechaNacimiento(), docente.getSexo(),
				docente.getNacionalidad(), docente.getDomicilio(), docente.getCodigoPostal(), docente.getDepartamento(),
				docente.getLocalidad(), docente.getContrasenia());

		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer update(Docente docente) {

		String sql = String.format(
				"UPDATE DOCENTES SET NOMBRE = '%s', APELLIDO = '%s', TIPO_DOCUMENTO = '%s', DNI = '%s', "
						+ "EMAIL = '%s', CELULAR = '%s', FECHA_NACIMIENTO = '%s', SEXO = '%s', NACIONALIDAD = '%s', DOMICILIO = '%s', "
						+ "CODIGO_POSTAL = '%s', DEPARTAMENTO = '%s', LOCALIDAD = '%s' " + "WHERE ID = '%s'",
				docente.getNombre(), docente.getApellido(), docente.getTipoDocumento(), docente.getDocumento(),
				docente.getEmail(), docente.getCelular(), docente.getFechaNacimiento(), docente.getSexo(),
				docente.getNacionalidad(), docente.getDomicilio(), docente.getCodigoPostal(), docente.getDepartamento(),
				docente.getLocalidad(), docente.getId());

		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer delete(Docente docente) {
		return 0;
	}

	private Docente toDocente(ResultSet resultados) {
		Docente docente = new Docente();
		try {
			docente.setId(resultados.getLong(1));
			docente.setNombre(resultados.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return docente;
	}

}

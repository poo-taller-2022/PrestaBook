package ar.edu.uner.prestabook.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ar.edu.uner.prestabook.model.Alumno;

public class AlumnoDAO implements IAlumnoDAO {

	Connection conn;

	public AlumnoDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Alumno> findAll() {
		String sql = "SELECT * FROM ALUMNOS";
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			List<Alumno> alumnos = new LinkedList<>();
			while (resultados.next()) {
				alumnos.add(toAlumno(resultados));
			}
			return alumnos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public Alumno findById(Object id) {

		String sql = String.format("SELECT * FROM ALUMNOS WHERE ID = %s", id.toString());
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			Alumno alumno = null;
			if (resultados.next()) {
				alumno = toAlumno(resultados);
			}
			return alumno;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer insert(Alumno alumno) {
		String sql = String.format(
				"INSERT INTO ALUMNOS (NOMBRE, APELLIDO, TIPO_DOCUMENTO, DNI,"
						+ "EMAIL, CELULAR, FECHA_NACIMIENTO, SEXO, NACIONALIDAD, DOMICILIO,"
						+ "CODIGO_POSTAL, DEPARTAMENTO, LOCALIDAD, CONTRASENIA) VALUES ('%s','%s','%s','%s','%s','%s',"
						+ "'%s','%s','%s','%s','%s','%s','%s','%s')",
				alumno.getNombre(), alumno.getApellido(), alumno.getTipoDocumento(), alumno.getDocumento(),
				alumno.getEmail(), alumno.getCelular(), alumno.getFechaNacimiento(), alumno.getSexo(),
				alumno.getNacionalidad(), alumno.getDomicilio(), alumno.getCodigoPostal(), alumno.getDepartamento(),
				alumno.getLocalidad(), alumno.getContrasenia());

		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer update(Alumno alumno) {

		String sql = String.format(
				"UPDATE ALUMNOS SET NOMBRE = '%s', APELLIDO = '%s', TIPO_DOCUMENTO = '%s', DNI = '%s', "
						+ "EMAIL = '%s', CELULAR = '%s', FECHA_NACIMIENTO = '%s', SEXO = '%s', NACIONALIDAD = '%s', DOMICILIO = '%s', "
						+ "CODIGO_POSTAL = '%s', DEPARTAMENTO = '%s', LOCALIDAD = '%s' " + "WHERE ID = '%s'",
				alumno.getNombre(), alumno.getApellido(), alumno.getTipoDocumento(), alumno.getDocumento(),
				alumno.getEmail(), alumno.getCelular(), alumno.getFechaNacimiento(), alumno.getSexo(),
				alumno.getNacionalidad(), alumno.getDomicilio(), alumno.getCodigoPostal(), alumno.getDepartamento(),
				alumno.getLocalidad(), alumno.getId());

		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer delete(Alumno alumno) {
		return 0;
	}

	private Alumno toAlumno(ResultSet resultados) {
		Alumno alumno = new Alumno();
		try {
			alumno.setId(resultados.getLong(1));
			alumno.setNombre(resultados.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alumno;
	}

}

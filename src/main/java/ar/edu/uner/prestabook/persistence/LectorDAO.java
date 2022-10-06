package ar.edu.uner.prestabook.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ar.edu.uner.prestabook.connection.ConnectionProvider;
import ar.edu.uner.prestabook.model.Lector;

public class LectorDAO implements ILectorDAO {

	Connection conn;
	
	public LectorDAO(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public List<Lector> findAll() {
		String sql = "SELECT * FROM LECTORES";
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			List<Lector> lectores = new LinkedList<>();
			while (resultados.next()) {
				lectores.add(toLector(resultados));
			}
			return lectores;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public Lector findById(Object id) {
		String sql = String.format("SELECT * FROM LECTORES WHERE ID = %s", id.toString());
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			ResultSet resultados = statement.executeQuery();
			Lector lector = null;
			if (resultados.next()) {
				lector = toLector(resultados);
			}
			return lector;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer insert(Lector lector) {
		
		String sql = String.format("INSERT INTO LECTORES (NOMBRE, APELLIDO, TIPO_DOCUMENTO, DNI,"
				+ "EMAIL, CELULAR, FECHA_NACIMIENTO, SEXO, NACIONALIDAD, DOMICILIO,"
				+ "CODIGO_POSTAL, DEPARTAMENTO, LOCALIDAD) VALUES ('%s','%s','%s','%s','%s','%s',"
				+ "'%s','%s','%s','%s','%s','%s','%s')", lector.getNombre(),lector.getApellido(),lector.getTipoDocumento(),
				lector.getDocumento(),lector.getEmail(),lector.getCelular(),lector.getFechaNacimiento(),lector.getSexo(),
				lector.getNacionalidad(),lector.getDomicilio(),lector.getCodigoPostal(),lector.getDepartamento(),lector.getLocalidad());
		
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer update(Lector lector) {
		
        String sql = String.format("UPDATE LECTORES SET NOMBRE = '%s', APELLIDO = '%s', TIPO_DOCUMENTO = '%s', DNI = '%s', "
                + "EMAIL = '%s', CELULAR = '%s', FECHA_NACIMIENTO = '%s', SEXO = '%s', NACIONALIDAD = '%s', DOMICILIO = '%s', "
                + "CODIGO_POSTAL = '%s', DEPARTAMENTO = '%s', LOCALIDAD = '%s' "
                + "WHERE ID = '%s'", lector.getNombre(),lector.getApellido(),lector.getTipoDocumento(),
				lector.getDocumento(),lector.getEmail(),lector.getCelular(),lector.getFechaNacimiento(),lector.getSexo(),
				lector.getNacionalidad(),lector.getDomicilio(),lector.getCodigoPostal(),lector.getDepartamento(),
				lector.getLocalidad(), lector.getId());
		
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer delete(Lector lector) {
		return 0;
	}
	
	private Lector toLector(ResultSet resultados) {
		Lector lector = new Lector();
		try {
			lector.setId(resultados.getLong(1));
			lector.setNombre(resultados.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lector;
	}

}

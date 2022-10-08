package ar.edu.uner.prestabook.model;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;

import ar.edu.uner.prestabook.common.DaoFactory;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lectores")
public class Lector extends Persona {

	@Id
	private Long id;

	public void registrarse(String tipoLector) {
		ModelMapper m = new ModelMapper();

		switch (tipoLector.toUpperCase()) {
		case "GENERAL":
			DaoFactory.getLectorDAO().insert(this);
			break;
		case "ALUMNO":
			Alumno alumno = m.map(this, Alumno.class);
			DaoFactory.getAlumnoDAO().insert(alumno);
			break;
		case "DOCENTE":
			Docente docente = m.map(this, Docente.class);
			DaoFactory.getDocenteDAO().insert(docente);
			break;
		default:
		}
	}

	public void reservarObra(Obra obra) {

	}

	public List<Obra> buscarObrasPorTema(String tema) {
		return Collections.emptyList();
	}

}
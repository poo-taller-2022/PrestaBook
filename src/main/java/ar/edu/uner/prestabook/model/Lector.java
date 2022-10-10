package ar.edu.uner.prestabook.model;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.persistence.IAlumnoDAO;
import ar.edu.uner.prestabook.persistence.IDocenteDAO;
import ar.edu.uner.prestabook.persistence.ILectorDAO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lectores")
@AllArgsConstructor
@NoArgsConstructor
public class Lector extends Persona {

	@Id
	private Long id;

	public void registrarse(String tipoLector, Lector lector) {
		ModelMapper m = new ModelMapper();

		switch (tipoLector.toUpperCase()) {
		case "PUBLICO GENERAL":
			ILectorDAO l = DaoFactory.getLectorDAO();
			l.insert(lector);
			break;
		case "ALUMNO":
			IAlumnoDAO a = DaoFactory.getAlumnoDAO();
			Alumno alumno = m.map(lector, Alumno.class);
			a.insert(alumno);
			break;
		case "DOCENTE":
			IDocenteDAO d = DaoFactory.getDocenteDAO();
			Docente docente = m.map(lector, Docente.class);
			d.insert(docente);
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
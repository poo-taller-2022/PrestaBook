package ar.edu.uner.prestabook.model;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;

import ar.edu.uner.prestabook.persistence.AlumnoDAO;
import ar.edu.uner.prestabook.persistence.DocenteDAO;
import ar.edu.uner.prestabook.persistence.LectorDAO;

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
		
		switch(tipoLector.toUpperCase()) {
			case "GENERAL":
				LectorDAO l = new LectorDAO();
				l.insert(this);
				break;
				
			case "ALUMNO":
				AlumnoDAO a = new AlumnoDAO();
				Alumno alumno = m.map(this, Alumno.class);
				
				a.insert(alumno);
				break;
			
			case "DOCENTE":
				DocenteDAO d = new DocenteDAO();
				Docente docente = m.map(this, Docente.class);
				d.insert(docente);
				break;
				
		}
	}
	
	public void reservarObra(Obra obra) {
		
	}

	public List<Obra> buscarObrasPorTema(String tema) {
		return Collections.emptyList();
	}
	
}
package ar.edu.uner.prestabook.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.modelmapper.ModelMapper;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.persistence.IAlumnoDAO;
import ar.edu.uner.prestabook.persistence.IDocenteDAO;
import ar.edu.uner.prestabook.persistence.IEjemplarDAO;
import ar.edu.uner.prestabook.persistence.ILectorDAO;
import ar.edu.uner.prestabook.persistence.IObraDAO;
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
	
    /**
     * 
     * @return list of Obras related by Tema
     * @param Tema the parameter Tema refers to AreaTematica
     */
	public List<Obra> buscarObrasPorTema(String tema) {
		IObraDAO obraDAO = DaoFactory.getObraDAO();
		List<Obra> obras = obraDAO.findAll();
		List<Obra> obrasFiltradas = new LinkedList<>();
		
		for(int i=0; i<obras.size(); i++) {
			if (obras.get(i).getArea().getNombre().equals(tema) ) {
				obrasFiltradas.add(obras.get(i));
			}
		}
		return obrasFiltradas;
	}
	
    /**
     * 
     * @return list of Ejemplares available
     * @param Isbn from an Obra
     */
	public List<Ejemplar> consultarEjemplares(String isbn) {
		IEjemplarDAO ejemplarDAO = DaoFactory.getEjemplarDAO();
		List<Ejemplar> ejemplaresDisponibles = ejemplarDAO.findAllByObraIsbn(isbn);
		return ejemplaresDisponibles;
	}
	
}
package ar.edu.uner.prestabook.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.connection.HibernateConnection;
import ar.edu.uner.prestabook.persistence.IAlumnoDAO;
import ar.edu.uner.prestabook.persistence.IAreaTematicaDAO;
import ar.edu.uner.prestabook.persistence.IDocenteDAO;
import ar.edu.uner.prestabook.persistence.ILectorDAO;
import ar.edu.uner.prestabook.persistence.IObraDAO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lectores")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Lector extends Persona {

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
    	IAreaTematicaDAO areaDAO = DaoFactory.getAreaTematicaDAO();
    	IObraDAO obraDAO = DaoFactory.getObraDAO();
    	Integer id = 0;
    	
    	List<AreaTematica> areas = areaDAO.findAll();
    	
    	for(int i=0; i<areas.size();i++) {
    		if(areas.get(i).getNombre().equals(tema)) {
    			id = areas.get(i).getId();
    			break;
    		}
    	}
    	
    	List<Obra> obras = obraDAO.filtrarPorTema(id);
    	return obras;
    }

}
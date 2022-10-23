package ar.edu.uner.prestabook.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PersistenceException;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import ar.edu.uner.prestabook.common.DaoFactory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lectores")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Lector extends Persona {

    public void registrarse(String tipoLector, Lector lector) {
        ModelMapper modelMapper = new ModelMapper();

        switch (tipoLector.toUpperCase()) {
            case "PUBLICO GENERAL":
                if (DaoFactory.getLectorDAO().findByEmail(lector.getEmail()) != null) {
                    throw new PersistenceException();
                }
                DaoFactory.getLectorDAO().insert(lector);
                break;
            case "ALUMNO":
                if (DaoFactory.getAlumnoDAO().findByEmail(lector.getEmail()) != null) {
                    throw new PersistenceException();
                }
                Alumno alumno = modelMapper.map(lector, Alumno.class);
                DaoFactory.getLectorDAO().insert(alumno);
                break;
            case "DOCENTE":
                if (DaoFactory.getDocenteDAO().findByEmail(lector.getEmail()) != null) {
                    throw new PersistenceException();
                }
                Docente docente = modelMapper.map(lector, Docente.class);
                DaoFactory.getLectorDAO().insert(docente);
                break;
            default:
        }
    }

}
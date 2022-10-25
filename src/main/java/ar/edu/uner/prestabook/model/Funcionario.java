package ar.edu.uner.prestabook.model;

import javax.persistence.Entity;
import javax.persistence.PersistenceException;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import ar.edu.uner.prestabook.common.DaoFactory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@ToString(callSuper = true)
@Table(name = "funcionarios")
public class Funcionario extends Persona {

	
	/**
	 * Registrarse method that allows saves an object of type Lector as Funcionario
	 * @param Lector Object Lector
	 */

    public void registrarse(Lector lector) {
        if (DaoFactory.getFuncionarioDAO().findByEmail(lector.getEmail()) != null) {
            throw new PersistenceException();
        } else {
            ModelMapper m = new ModelMapper();
            Funcionario funcionario = m.map(lector, Funcionario.class);
            DaoFactory.getFuncionarioDAO().insert(funcionario);
        }
    }
}

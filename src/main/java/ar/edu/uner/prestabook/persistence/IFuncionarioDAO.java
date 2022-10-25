package ar.edu.uner.prestabook.persistence;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Funcionario;

public interface IFuncionarioDAO extends GenericDAO<Funcionario> {

	/**
	 * Finds a Funcionario by its email
	 * @param email String that represent the email to search
	 * @return entity of type Funcionario if the email matches the database field, null if it does not
	 */
    public Funcionario findByEmail(String email);

}

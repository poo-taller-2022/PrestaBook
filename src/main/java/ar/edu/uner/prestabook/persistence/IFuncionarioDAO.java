package ar.edu.uner.prestabook.persistence;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Funcionario;

public interface IFuncionarioDAO extends GenericDAO<Funcionario> {

    Funcionario findByEmail(String text);

}

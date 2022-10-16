package ar.edu.uner.prestabook.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import ar.edu.uner.prestabook.common.DaoFactory;
import ar.edu.uner.prestabook.persistence.ICodigoIdentificatorioDAO;
import ar.edu.uner.prestabook.persistence.IFuncionarioDAO;
import ar.edu.uner.prestabook.persistence.ITipoObraDAO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@ToString(callSuper = true)
@Table(name = "funcionarios")
public class Funcionario extends Persona {

    public void registrarse(Lector lector) {
        ModelMapper m = new ModelMapper();

        IFuncionarioDAO f = DaoFactory.getFuncionarioDAO();
        Funcionario funcionario = m.map(lector, Funcionario.class);
        f.insert(funcionario);
    }

	public void cargarTipoObra(TipoObra obra) {
		ITipoObraDAO t = DaoFactory.getTipoObraDAO();
		t.insert(obra);
	}

	public void cargarCodigoIdentificatorio(CodigoIdentificatorio codigo) {
		ICodigoIdentificatorioDAO c = DaoFactory.getCodigoIdentificatorioDAO();
		c.insert(codigo);

	}

	public void prestarADomicilio(Obra obra, Integer plazo) {
		/*
		 * TODO En este metodo deberiamos pasar por parametro tambien al lector, algo
		 * que no anotamos en el uml
		 */
	}

    public void prestarEnSala(Obra obra) {
        /*
         * TODO En este metodo deberiamos pasar por parametro tambien al lector, algo
         * que no anotamos en el uml
         */
    }

    public void gestionarDevolucion(Obra obra) {
        /*
         * TODO En este metodo deberiamos pasar por parametro tambien al lector, algo
         * que no anotamos en el uml
         */
    }

    public List<Lector> listarLectoresMorosos() {
        return Collections.emptyList();
    }

    public List<Obra> listarMasBuscadasPorAlumnosYDocentes() {
        return Collections.emptyList();
    }

    public List<Obra> listarMasBuscadasPorPublicoGeneral() {
        return Collections.emptyList();
    }

    public List<Ejemplar> listarEjemplaresDisponiblesPorArea(AreaTematica area) {
        return Collections.emptyList();
    }

    public List<Obra> listarObrasReservadasAPartirDe(String fecha) {
        return Collections.emptyList();
    }

    public List<Lector> listarMultadosPorPeriodo(String inicio, String fin) {
        return Collections.emptyList();
    }

    public List<Lector> listarRankingDeMultados() {
        return Collections.emptyList();
    }

    public List<Obra> listarObrasPorEditorial(String editorial) {
        return Collections.emptyList();
    }

    public void aplicarMulta(Lector lector) {
    }

}

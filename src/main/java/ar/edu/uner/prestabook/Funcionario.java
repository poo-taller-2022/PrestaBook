package ar.edu.uner.prestabook;

import java.util.Collections;
import java.util.List;

public class Funcionario extends Persona {

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

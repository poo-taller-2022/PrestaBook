package ar.edu.uner.prestabook;

import java.util.Collections;
import java.util.List;

import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
public class Lector extends Persona {

	public void reservarObra(Obra obra) {
	}

	public List<Obra> buscarObrasPorTema(String tema) {
		return Collections.emptyList();
	}

}

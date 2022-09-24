package ar.edu.uner.prestabook.model;

import java.util.Collections;
import java.util.List;

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

	public void reservarObra(Obra obra) {
	}

	public List<Obra> buscarObrasPorTema(String tema) {
		return Collections.emptyList();
	}

}

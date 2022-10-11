package ar.edu.uner.prestabook.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "obras")
public class Obra {

	@Id
	private String isbn;
	private List<Ejemplar> ejemplares;
	private String titulo;
	private String subtitulo;
	private String primerAutor;
	private String segundoAutor;
	private String tercerAutor;
	private String genero;
	private TipoObra tipo;
	private AreaTematica area;

}

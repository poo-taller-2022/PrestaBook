package ar.edu.uner.prestabook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "obras")
public class Obra {

	@Id
	private String isbn;
	private Ejemplar[] ejemplares;
	private String titulo;
	private String subtitulo;
	private String primerAutor;
	private String segundoAutor;
	private String tercerAutor;
	private String genero;
	private TipoObra tipo;
	private AreaTematica area;

}
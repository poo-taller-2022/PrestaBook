package ar.edu.uner.prestabook;

import lombok.Data;

@Data
public class Obra {
	
	private Ejemplar[] ejemplares;
	private String titulo;
	private String subtitulo;
	private String primerAutor;
	private String segundoAutor;
	private String tercerAutor;
	private String genero;
	private String isbn;
	private TipoObra tipo;
	private AreaTematica area;

}

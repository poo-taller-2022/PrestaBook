package ar.edu.uner.prestabook.jframe;

import lombok.Data;

@Data
public class ModeloDeTransferencia {
	String fieldIsbn;
	String fieldTitulo;
	String fieldSubtitulo;
	String fieldPrimerAutor;
	String fieldSegundoAutor;
	String fieldTercerAutor;
	String fieldGenero;
	String fielTipoObra;
	String fielAreaTematica;
	Integer idTipoObra;
	Integer idAreaTematica;
	String fieldeditorial;
	String fieldpais;
	String fieldnumero;
	String fieldanio;
	String fieldvolumenes;
	String fieldpaginas;
	String fieldidioma;
	String fielformato;
	Integer idFormato;
	String fieldFormaAdquisicion;
	String fieldFechaAdquisicion;
	String fieldObservaciones;
	Boolean refrescar;
}

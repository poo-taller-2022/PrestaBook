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
	String fieldFormaAdquisicion;
	String fieldFechaAdquisicion;
	String fieldObservaciones;
	Integer idTipoObra;
	Integer idAreaTematica;
	Integer idFormato;
	Boolean refrescar;
}

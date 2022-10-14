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
	String comboBoxTipoObra;
	String comboBoxAreaTematica;
	String comboBoxFormato; 
	String fieldFormaAdquisicion;
	String fieldFechaAdquisicion;
	String fieldObservaciones;
	Boolean refrescar;
}

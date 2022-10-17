package ar.edu.uner.prestabook.jframe;

import java.util.List;

import ar.edu.uner.prestabook.model.Formato;
import ar.edu.uner.prestabook.model.Obra;
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
	List<Formato> formatos;
	Integer idFormato;
	String fieldFormaAdquisicion;
	String fieldFechaAdquisicion;
	String fieldObservaciones;
	Integer fieldCodigo;
	Integer fieldPasillo;
	Integer fieldEstanteria;
	Integer fieldEstante;
	Obra ObraSeleccionada;
	Boolean refrescar;
}

package ar.edu.uner.prestabook;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Ejemplar extends Obra {

	private String formaAdquisicion;
	private String fechaAdquisicion;
	private String observaciones;
	private String fechaBaja;
	private String motivoBaja;
	private String ubicacion;
	private CodigoIdentificatorio codigoIdentificatorio;

}

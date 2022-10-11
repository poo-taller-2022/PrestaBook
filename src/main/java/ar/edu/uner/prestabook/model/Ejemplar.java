package ar.edu.uner.prestabook.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ejemplares")
public class Ejemplar extends Obra {

	private CodigoIdentificatorio codigoIdentificatorio;
	private String formaAdquisicion;
	private String fechaAdquisicion;
	private String observaciones;
	private String fechaBaja;
	private String motivoBaja;
	private String ubicacion;

}

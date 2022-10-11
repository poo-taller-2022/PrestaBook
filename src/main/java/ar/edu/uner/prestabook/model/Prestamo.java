package ar.edu.uner.prestabook.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "prestamos")
public class Prestamo {

	private String fechaYHoraPrestamo;
	private String fechaPactadaDevolucion;
	private String fechaRealDevolucion;
	private Integer plazoPrestamo;
	private Boolean fueraDeTermino;

}

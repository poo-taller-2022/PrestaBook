package ar.edu.uner.prestabook;

import lombok.Data;

@Data
public class Prestamo {

	private String fechaYHoraPrestamo;
	private String fechaPactadaDevolucion;
	private String fechaRealDevolucion;
	private Integer plazoPrestamo;
	private Boolean fueraDeTermino;
	
}

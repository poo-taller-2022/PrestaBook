package ar.edu.uner.prestabook;

import lombok.Data;

@Data
public class Edicion {

	private String editorial;
	private String pais;
	private Integer numero;
	private Integer anio;
	private Long volumenes;
	private Integer paginas;
	private String idioma;
	private Formato[] formatos;

}

package ar.edu.uner.prestabook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ediciones")
public class Edicion {

	private Long id;
	private String editorial;
	private String pais;
	private Integer numero;
	private Integer anio;
	private Long volumenes;
	private Integer paginas;
	private String idioma;
	private Formato[] formatos;

}

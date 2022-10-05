package ar.edu.uner.prestabook.model;

import java.util.List;

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
<<<<<<< HEAD
	private Formato formatos;
	
	public Edicion(Long id, String editorial, String pais, Integer numero, Integer anio, Long volumenes,
			Integer paginas, String idioma, Formato formatos) {
		super();
		this.id = id;
		this.editorial = editorial;
		this.pais = pais;
		this.numero = numero;
		this.anio = anio;
		this.volumenes = volumenes;
		this.paginas = paginas;
		this.idioma = idioma;
		this.formatos = formatos;
	}
	
=======
	private List<Formato> formatos;

>>>>>>> 30adcef8e86f59b4df64ba7e150a5581db6d5597
}

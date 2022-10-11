package ar.edu.uner.prestabook.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ediciones")
@AllArgsConstructor
@NoArgsConstructor
public class Edicion {

	private Long id;
	private String editorial;
	private String pais;
	private Integer numero;
	private Integer anio;
	private Long volumenes;
	private Integer paginas;
	private String idioma;
	private List<Formato> formatos;
}

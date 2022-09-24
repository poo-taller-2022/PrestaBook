package ar.edu.uner.prestabook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "multas")
public class Multa {

	@Id
	private Long id;
	private Integer plazo;
	private String fecha;
	
}

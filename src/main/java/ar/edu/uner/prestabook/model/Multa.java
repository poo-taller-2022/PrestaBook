package ar.edu.uner.prestabook.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

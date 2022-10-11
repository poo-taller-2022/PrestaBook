package ar.edu.uner.prestabook.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "formato")
public class Formato {

	@Id
	private Long id;
	private String nombre;

}

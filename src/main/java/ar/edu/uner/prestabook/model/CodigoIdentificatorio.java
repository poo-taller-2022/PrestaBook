package ar.edu.uner.prestabook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "codigos_identificatorios")
@AllArgsConstructor
@NoArgsConstructor
public class CodigoIdentificatorio {

	private Long codigo;
	private Integer pasillo;
	private Integer estanteria;
	private Integer estante;

}

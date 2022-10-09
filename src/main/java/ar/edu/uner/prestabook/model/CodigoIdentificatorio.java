package ar.edu.uner.prestabook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "codigos_identificatorios")
public class CodigoIdentificatorio {

	private String codigo;
	private String pasillo;
	private String estanteria;
	private String estante;

}

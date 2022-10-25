package ar.edu.uner.prestabook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Base class that represents a class of type CodigoIdentificatorio
*
*/


@Data
@Entity
@Table(name = "codigos_identificatorios")
@AllArgsConstructor
@NoArgsConstructor
public class CodigoIdentificatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long codigo;
    private Integer pasillo;
    private Integer estanteria;
    private Integer estante;
    @Override
	public String toString() {
		return  (codigo.toString()    + pasillo.toString()  + estanteria.toString()
				 + estante.toString()) ;
	}
}

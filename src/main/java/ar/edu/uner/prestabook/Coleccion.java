package ar.edu.uner.prestabook;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Coleccion extends Obra {

	private Obra[] obras;

}

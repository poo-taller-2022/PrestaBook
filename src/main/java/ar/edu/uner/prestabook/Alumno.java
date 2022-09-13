package ar.edu.uner.prestabook;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Alumno extends Lector {

	private List<String> carreras;
}

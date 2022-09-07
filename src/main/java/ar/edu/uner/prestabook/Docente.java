package ar.edu.uner.prestabook;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Docente extends Lector {

	private List<String> carreras;
}

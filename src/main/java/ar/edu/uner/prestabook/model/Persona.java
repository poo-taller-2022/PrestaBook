package ar.edu.uner.prestabook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Persona {

	private String nombre;
	private String apellido;
	private String tipoDocumento;
	private String documento;
	private String email;
	private String celular;
	private String fechaNacimiento;
	private String sexo;
	private String nacionalidad;
	private String domicilio;
	private String codigoPostal;
	private String departamento;
	private String localidad;
	private String contrasenia;
}

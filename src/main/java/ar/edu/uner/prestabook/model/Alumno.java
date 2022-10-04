package ar.edu.uner.prestabook.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "alumnos")
public class Alumno extends Lector {

	@Id
	private Long id;
	private List<String> carreras;
	
	public Alumno() {
		
	}
	
	public Alumno(Lector lector) {
		this.setNombre(lector.getNombre());
		this.setApellido(lector.getApellido());
		this.setTipoDocumento(lector.getTipoDocumento());
		this.setDocumento(lector.getDocumento());
		this.setEmail(lector.getEmail());
		this.setCelular(lector.getCelular());
		this.setFechaNacimiento(lector.getFechaNacimiento());
		this.setSexo(lector.getSexo());
		this.setNacionalidad(lector.getNacionalidad());
		this.setDomicilio(lector.getDomicilio());
		this.setCodigoPostal(lector.getCodigoPostal());
		this.setDepartamento(lector.getDepartamento());
		this.setLocalidad(lector.getLocalidad());
	}
}

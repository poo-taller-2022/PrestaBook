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
@Table(name = "docentes")
public class Docente extends Lector {

	@Id
	private Long id;
	private List<String> carreras;
	
	public Docente() {
		
	}
	
	public Docente(Lector lec) {
		this.setNombre(lec.getNombre());
		this.setApellido(lec.getApellido());
		this.setTipoDocumento(lec.getTipoDocumento());
		this.setDocumento(lec.getDocumento());
		this.setEmail(lec.getEmail());
		this.setCelular(lec.getCelular());
		this.setFechaNacimiento(lec.getFechaNacimiento());
		this.setSexo(lec.getSexo());
		this.setNacionalidad(lec.getNacionalidad());
		this.setDomicilio(lec.getDomicilio());
		this.setCodigoPostal(lec.getCodigoPostal());
		this.setDepartamento(lec.getDepartamento());
		this.setLocalidad(lec.getLocalidad());
	}
}

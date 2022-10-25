package ar.edu.uner.prestabook.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
* Base class that represents a class of type Persona
*
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Persona {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private Long documento;
    private String nombre;
    private String apellido;
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Column(nullable = false, unique = true)
    private String email;
    private String celular;
    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;
    private String sexo;
    private String nacionalidad;
    private String domicilio;
    @Column(name = "codigo_postal")
    private String codigoPostal;
    private String departamento;
    private String localidad;
    private String contrasenia;
}

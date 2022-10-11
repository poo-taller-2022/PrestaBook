package ar.edu.uner.prestabook.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Persona {

    private String nombre;
    private String apellido;
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    private String documento;
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

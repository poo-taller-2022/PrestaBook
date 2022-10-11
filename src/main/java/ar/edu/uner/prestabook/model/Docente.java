package ar.edu.uner.prestabook.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "docentes")
@AllArgsConstructor
@NoArgsConstructor
public class Docente extends Lector {
    
    // TODO: ver la posibilidad de crear una entidad Carrera
    private String carreras;
}
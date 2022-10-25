package ar.edu.uner.prestabook.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* Base class that represents a class of type Docente and extends of Lector
*
*/


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "docentes")
@AllArgsConstructor
@NoArgsConstructor
public class Docente extends Lector {

    private String carreras;
}
package ar.edu.uner.prestabook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* Base class that represents a class of type Formato
*
*/

@Data
@Entity
@Table(name = "formato")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Formato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private Integer id;
    private String nombre;

}

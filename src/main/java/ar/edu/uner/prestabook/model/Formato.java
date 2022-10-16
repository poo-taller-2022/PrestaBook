package ar.edu.uner.prestabook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "formato")
public class Formato {

    @Id
    @TableGenerator(name = "pb_sequence")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pb_sequence")
    @Column(unique = true, nullable = false)
    private Integer id;
    private String nombre;

}

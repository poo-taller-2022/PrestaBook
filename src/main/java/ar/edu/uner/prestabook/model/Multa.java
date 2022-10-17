package ar.edu.uner.prestabook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "multas")
public class Multa {

    @Id
    @TableGenerator(name = "pb_sequence")
       @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Integer plazo;
    private String fecha;
    @ManyToOne
    private Lector lector;
    @ManyToOne
    private Prestamo prestamo;

}

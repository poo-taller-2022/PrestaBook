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
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @TableGenerator(name = "pb_sequence")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pb_sequence")
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(name = "fecha_y_hora_prestamo")
    private String fechaYHoraPrestamo;
    @Column(name = "fecha_pactada_devolucion")
    private String fechaPactadaDevolucion;
    @Column(name = "fecha_real_devolucion")
    private String fechaRealDevolucion;
    @Column(name = "plazo_prestamo")
    private Integer plazoPrestamo;
    @Column(name = "fuera_de_termino")
    private Boolean fueraDeTermino;
    @ManyToOne
    private Ejemplar ejemplar;
    @ManyToOne
    private Lector lector;
    @ManyToOne
    private Funcionario funcionario;

}

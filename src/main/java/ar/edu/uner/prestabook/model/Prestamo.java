package ar.edu.uner.prestabook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}

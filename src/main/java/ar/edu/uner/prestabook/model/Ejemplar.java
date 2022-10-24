package ar.edu.uner.prestabook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
* Base class that represents a class of type Ejemplar
*
*/

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ejemplares")
@ToString(callSuper = true)
public class Ejemplar extends ObraAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;
    @OneToOne
    @JoinColumn(name = "codigo_identificatorio")
    private CodigoIdentificatorio codigoIdentificatorio;
    @Column(name = "forma_adquisicion")
    private String formaAdquisicion;
    @Column(name = "fecha_adquisicion")
    private String fechaAdquisicion;
    private String observaciones;
    @Column(name = "fecha_baja")
    private String fechaBaja;
    @Column(name = "motivo_baja")
    private String motivoBaja;
    @Column(name = "isbn_obra")
    private String isbnObra;
    @Column(name = "id_Edicion")
    private Long idEdicion;

}

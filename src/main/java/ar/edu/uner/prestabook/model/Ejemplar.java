package ar.edu.uner.prestabook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ejemplares")
@ToString(callSuper = true)
@TableGenerator(name = "pb_sequence")
public class Ejemplar extends Obra {

    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pb_sequence")
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
    private String ubicacion;


}

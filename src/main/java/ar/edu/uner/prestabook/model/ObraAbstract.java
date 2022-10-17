package ar.edu.uner.prestabook.model;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class ObraAbstract {

    private String titulo;
    private String subtitulo;
    @Column(name = "primer_autor")
    private String primerAutor;
    @Column(name = "segundo_autor")
    private String segundoAutor;
    @Column(name = "tercer_autor")
    private String tercerAutor;
    private String genero;
    @ManyToOne
    private TipoObra tipo;
    @ManyToOne
    private AreaTematica area;

}

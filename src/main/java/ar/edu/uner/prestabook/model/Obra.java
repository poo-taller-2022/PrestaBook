package ar.edu.uner.prestabook.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "obras")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;
    @Column(nullable = false)
    private String isbn;
    @OneToMany
    private List<Ejemplar> ejemplares;
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
    @OneToMany
    private List<Edicion> edicion;

}

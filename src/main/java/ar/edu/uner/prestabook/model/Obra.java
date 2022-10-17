package ar.edu.uner.prestabook.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "obras")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Obra extends ObraAbstract {

    @Id
    @Column(nullable = false, unique = true)
    private String isbn;
    @OneToMany(mappedBy = "isbnObra")
    private List<Ejemplar> ejemplares;
    @Column(name = "isbn_coleccion")
    private String isbnColeccion;
    @OneToMany(mappedBy = "isbnObra")
    private List<Edicion> edicion;

}

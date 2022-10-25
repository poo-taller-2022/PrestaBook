package ar.edu.uner.prestabook.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;




/**
* Base class that represents a class of type Obra and extends of ObraAbstract
*
*/

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
    @ManyToMany
    @JoinTable(
            name = "obras_areas_tematicas", 
            joinColumns = @JoinColumn(name = "Obra_isbn"), 
            inverseJoinColumns = @JoinColumn(name = "area_id"))
    private Set<AreaTematica> area;

}

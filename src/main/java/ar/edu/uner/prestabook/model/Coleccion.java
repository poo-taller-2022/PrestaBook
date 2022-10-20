package ar.edu.uner.prestabook.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "colecciones")
public class Coleccion extends ObraAbstract {

    @Id
    @Column(nullable = false, unique = true)
    private String isbn;
    @OneToMany(mappedBy = "isbnColeccion")
    private List<Obra> obras;
    
    @ManyToMany
    @JoinTable(
            name = "colecciones_areas_tematicas", 
            joinColumns = @JoinColumn(name = "Coleccion_isbn"), 
            inverseJoinColumns = @JoinColumn(name = "area_id"))
    private Set<AreaTematica> area;

}

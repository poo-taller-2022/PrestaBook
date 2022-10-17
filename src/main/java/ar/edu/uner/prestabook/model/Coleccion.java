package ar.edu.uner.prestabook.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @OneToMany
	private List<Obra> obras;

}

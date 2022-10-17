package ar.edu.uner.prestabook.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ediciones")
@AllArgsConstructor
@NoArgsConstructor
public class Edicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String editorial;
    private String pais;
    private Integer numero;
    private Integer anio;
    private Integer volumenes;
    private Integer paginas;
    private String idioma;
    @ManyToMany
    @JoinTable(
            name = "ediciones_formato", 
            joinColumns = @JoinColumn(name = "edicion_id"), 
            inverseJoinColumns = @JoinColumn(name = "formato_id"))
    private Set<Formato> formatos;
    @Column(name = "isbn_obra")
    private String isbnObra;
}

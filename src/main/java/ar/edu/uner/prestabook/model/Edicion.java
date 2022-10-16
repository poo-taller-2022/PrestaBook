package ar.edu.uner.prestabook.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

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
    @TableGenerator(name = "pb_sequence")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pb_sequence")
    @Column(unique = true, nullable = false)
    private Long id;
    private String editorial;
    private String pais;
    private Integer numero;
    private Integer anio;
    private Long volumenes;
    private Integer paginas;
    private String idioma;
    @ManyToMany
    private List<Formato> formatos;
}

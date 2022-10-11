package ar.edu.uner.prestabook.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tipos_obra")
@AllArgsConstructor
@NoArgsConstructor
public class TipoObra {

    @Id
    private Long id;
    private String nombre;
}

package ar.edu.uner.prestabook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

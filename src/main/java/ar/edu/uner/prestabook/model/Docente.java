package ar.edu.uner.prestabook.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "docentes")
@AllArgsConstructor
@NoArgsConstructor
public class Docente extends Lector {

	@Id
	private Long id;
	private List<String> carreras;
}
package ar.edu.uner.prestabook.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
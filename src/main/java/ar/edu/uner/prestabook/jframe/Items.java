package ar.edu.uner.prestabook.jframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items {
	private Integer id;
	private String valor;

	@Override
	public String toString() {
		return valor;
	}
}

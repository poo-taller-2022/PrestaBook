package ar.edu.uner.prestabook.jframe.render;

import java.awt.Component;
import java.util.Optional;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import ar.edu.uner.prestabook.model.Persona;



/**
 * 
 * Class that handles the combobox rendering of Persona
 *
 */
public class PersonaRenderer extends BasicComboBoxRenderer {

	private static final long serialVersionUID = 1L;

	@Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected,
                cellHasFocus);
        Persona item = (Persona) value;
        if (item != null)
            setText(String.format("%s %s", Optional.of(item.getNombre()).orElse(""),
                    Optional.of(item.getApellido()).orElse("")));
        return this;
    }

}

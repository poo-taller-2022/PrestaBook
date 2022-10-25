package ar.edu.uner.prestabook.jframe.render;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import ar.edu.uner.prestabook.model.Ejemplar;


/**
 * 
 * Class that handles the combobox renderization of Ejemplar
 *
 */

public class EjemplarRenderer extends BasicComboBoxRenderer {

	private static final long serialVersionUID = 1L;

	@Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected,
                cellHasFocus);
        Ejemplar item = (Ejemplar) value;
        if (item != null)
            setText(item.getCodigoIdentificatorio().getCodigo().toString());
        return this;
    }

}

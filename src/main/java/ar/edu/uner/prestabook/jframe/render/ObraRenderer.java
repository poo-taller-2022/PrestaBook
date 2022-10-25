package ar.edu.uner.prestabook.jframe.render;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import ar.edu.uner.prestabook.model.ObraAbstract;




/**
 * 
 * Class that handles the combobox rendering of Obra
 *
 */
public class ObraRenderer extends BasicComboBoxRenderer {

	private static final long serialVersionUID = 1L;

	@Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected,
                cellHasFocus);
        ObraAbstract item = (ObraAbstract) value;
        if (item != null)
            setText(item.getTitulo());
        return this;
    }

}

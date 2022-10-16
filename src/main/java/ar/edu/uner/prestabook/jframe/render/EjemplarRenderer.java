package ar.edu.uner.prestabook.jframe.render;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import ar.edu.uner.prestabook.model.Ejemplar;

public class EjemplarRenderer extends BasicComboBoxRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected,
                cellHasFocus);
        Ejemplar item = (Ejemplar) value;
        if (item != null)
            setText(item.getCodigoIdentificatorio().getId().toString());
        return this;
    }

}

package ar.edu.uner.prestabook.jframe.panels;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.common.Components;

public class PanelAreaTematica extends AbstractPanel {

    public JPanel init() {
        prepare();
        JButton btnAreaTematica = Components.btnGeneric("Agregar Area Tematica", "Left");
        JButton btnActualizarAreaTematica = Components.btnGeneric("Refrescar", "Right");

        panel.add(btnAreaTematica);
        panel.add(btnActualizarAreaTematica);

        btnAreaTematica.addActionListener(b -> {
            String valorAgregar = JOptionPane.showInputDialog(null, "Ingresar nombre de area tematica");
            if (valorAgregar != null && !valorAgregar.isBlank()) {
                Components.updateDatabase(valorAgregar, Constants.AREA_TEMATICA);
                Components.clearTable(table);
                Tabla.fill(model, Constants.AREA_TEMATICA);
            }
        });
        return panel;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn(Constants.NOMBRE);
        //Components.clearTable(table);
        Tabla.fill(model, Constants.AREA_TEMATICA);
    }

    @Override
    public String getPanelName() {
        return Constants.AREAS_TEMATICAS;
    }

}

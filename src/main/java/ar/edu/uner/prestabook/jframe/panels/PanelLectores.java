package ar.edu.uner.prestabook.jframe.panels;

import javax.swing.JButton;
import javax.swing.JPanel;

import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.common.Components;

public class PanelLectores extends AbstractPanel {

    @Override
    public JPanel init() {
        prepare();

        JButton btnRefrescar = Components.btnGeneric("Refrescar", "Right");
        panel.add(btnRefrescar);

        btnRefrescar.addActionListener(b -> {
            Components.clearTable(table);
            Tabla.fill(model, Constants.LECTOR);
        });
        return panel;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("E-mail");
        model.addColumn("Dirección");
        model.addColumn("Fecha de nacimiento");
        model.addColumn("Multas");
        Tabla.fill(model, Constants.LECTOR);

    }

    @Override
    public String getPanelName() {
        return Constants.LECTORES;
    }

}

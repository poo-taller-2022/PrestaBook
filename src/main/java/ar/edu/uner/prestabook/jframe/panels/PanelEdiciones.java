package ar.edu.uner.prestabook.jframe.panels;

import javax.swing.JButton;
import javax.swing.JPanel;

import ar.edu.uner.prestabook.jframe.AgregarEdicion;
import ar.edu.uner.prestabook.jframe.Constants;
import ar.edu.uner.prestabook.jframe.Tabla;
import ar.edu.uner.prestabook.jframe.common.Components;

public class PanelEdiciones extends AbstractPanel {

    @Override
    public JPanel init() {

        prepare();
        JButton btnAgregarEdicion = Components.btnGeneric("Agregar edición", "Left");
        JButton btnRefrescar = Components.btnGeneric("Refrescar", "Right");
        panel.add(btnAgregarEdicion);
        panel.add(btnRefrescar);

        btnAgregarEdicion.addActionListener(b -> {
            AgregarEdicion agregarEdicion = new AgregarEdicion(table, model);
            agregarEdicion.setVisible(true);
        });

        btnRefrescar.addActionListener(b -> {
            Components.clearTable(table);
            Tabla.fill(model, Constants.EDICION);
        });

        return panel;
    }

    @Override
    public void setModelColumns() {
        model.addColumn("");
        model.addColumn("Editorial");
        model.addColumn("Pais");
        model.addColumn("Numero");
        model.addColumn("Año");
        model.addColumn("Volumenes");
        model.addColumn("Paginas");
        model.addColumn("Idioma");
        model.addColumn("Formato");
        Tabla.fill(model, Constants.EDICION);
    }

    @Override
    public String getPanelName() {
        return Constants.EDICIONES;
    }

}
